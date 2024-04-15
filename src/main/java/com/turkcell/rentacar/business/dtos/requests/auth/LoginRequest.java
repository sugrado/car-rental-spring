package com.turkcell.rentacar.business.dtos.requests.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LoginRequest {
    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 8, max = 16)
    private String password;
}
