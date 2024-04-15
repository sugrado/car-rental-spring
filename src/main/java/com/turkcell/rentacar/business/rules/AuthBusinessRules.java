package com.turkcell.rentacar.business.rules;

import com.turkcell.rentacar.business.constants.messages.AuthMessages;
import com.turkcell.rentacar.core.utilities.exceptions.types.BusinessException;
import com.turkcell.rentacar.dataAccess.abstracts.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthBusinessRules {
    private final UserRepository userRepository;

    public void UserEmailShouldNotBeExists(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new BusinessException(AuthMessages.USER_MAIL_ALREADY_EXISTS);
        }
    }
}
