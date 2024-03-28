package com.turkcell.rentacar.business.dtos.requests.payments;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePaymentRequest {
    @NotNull
    private int rentalId;

    @NotNull
    private double amount;

    @NotNull
    private CreditCardDto creditCard;
}
