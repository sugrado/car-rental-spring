package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.auth.LoginRequest;
import com.turkcell.rentacar.business.dtos.requests.auth.RegisterRequest;
import com.turkcell.rentacar.business.dtos.responses.auth.LoggedInResponse;
import com.turkcell.rentacar.business.dtos.responses.auth.RefreshedTokenResponse;
import com.turkcell.rentacar.business.dtos.responses.auth.RegisteredResponse;

public interface AuthService {
    LoggedInResponse login(LoginRequest loginRequest, String ipAddress);

    RefreshedTokenResponse refreshToken(String refreshToken, String ipAddress);

    RegisteredResponse register(RegisterRequest request);
}
