package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.AuthService;
import com.turkcell.rentacar.business.abstracts.RefreshTokenService;
import com.turkcell.rentacar.business.abstracts.UserService;
import com.turkcell.rentacar.business.constants.messages.AuthMessages;
import com.turkcell.rentacar.business.dtos.requests.auth.LoginRequest;
import com.turkcell.rentacar.business.dtos.requests.auth.RegisterRequest;
import com.turkcell.rentacar.business.dtos.responses.auth.LoggedInResponse;
import com.turkcell.rentacar.business.dtos.responses.auth.RefreshedTokenResponse;
import com.turkcell.rentacar.business.dtos.responses.auth.RegisteredResponse;
import com.turkcell.rentacar.business.rules.AuthBusinessRules;
import com.turkcell.rentacar.core.services.JwtService;
import com.turkcell.rentacar.core.utilities.exceptions.types.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.entities.concretes.RefreshToken;
import com.turkcell.rentacar.entities.concretes.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class AuthManager implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final AuthBusinessRules authBusinessRules;
    private final UserService userService;
    private final RefreshTokenService refreshTokenService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final ModelMapperService modelMapperService;

    @Override
    public RegisteredResponse register(RegisterRequest request) {
        User user = modelMapperService.forRequest().map(request, User.class);
        authBusinessRules.UserEmailShouldNotBeExists(user.getEmail());
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        user.setPassword(encodedPassword);
        User createdUser = userService.add(user);
        RefreshToken refreshToken = refreshTokenService.create(createdUser);
        String accessToken = generateJwt(createdUser);
        return new RegisteredResponse(accessToken, refreshToken.getToken());
    }

    @Override
    public LoggedInResponse login(LoginRequest loginRequest, String ipAddress) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        if (!authentication.isAuthenticated()) {
            throw new BusinessException(AuthMessages.LOGIN_FAILED);
        }
        User user = userService.findByUsername(loginRequest.getEmail());
        refreshTokenService.revokeOldTokens(user, ipAddress);
        RefreshToken refreshToken = refreshTokenService.create(user);
        String accessToken = generateJwt(user);
        return new LoggedInResponse(accessToken, refreshToken.getToken());
    }

    @Override
    public RefreshedTokenResponse refreshToken(String refreshToken, String ipAddress) {
        RefreshToken token = refreshTokenService.verify(refreshToken);
        RefreshToken newToken = refreshTokenService.rotate(token, ipAddress);
        refreshTokenService.revokeOldTokens(token.getUser(), ipAddress);
        String accessToken = generateJwt(token.getUser());
        return new RefreshedTokenResponse(accessToken, newToken.getToken());
    }

    private String generateJwt(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("username", user.getEmail());
        return jwtService.generateToken(user.getEmail(), claims);
    }
}