package com.turkcell.rentacar.business.dtos.requests.payments;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateWithoutPaymentRequest {
    @NotNull
    private int rentalId;

    @NotNull
    private double amount;
}
