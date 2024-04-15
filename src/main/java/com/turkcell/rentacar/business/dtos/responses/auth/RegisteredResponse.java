package com.turkcell.rentacar.business.dtos.responses.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisteredResponse {
    private String accessToken;
    private String refreshToken;
}
