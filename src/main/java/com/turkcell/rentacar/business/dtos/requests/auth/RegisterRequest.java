package com.turkcell.rentacar.business.dtos.requests.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterRequest {
    @Email
    @NotNull
    private String email;

    @NotNull
    @Size(min = 8, max = 16)
    private String password;

    @NotNull
    private LocalDate birthDate;
}
