package com.turkcell.rentacar.business.dtos.requests.payments;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateWithoutPaymentRequest {
    @NotNull
    private int rentalId;

    @NotNull
    private double amount;
}
