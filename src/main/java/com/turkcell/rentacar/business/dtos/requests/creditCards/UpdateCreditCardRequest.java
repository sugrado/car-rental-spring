package com.turkcell.rentacar.business.dtos.requests.creditCards;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCreditCardRequest {
    @NotNull
    @Size(min = 2, max = 16)
    private String name;
}
