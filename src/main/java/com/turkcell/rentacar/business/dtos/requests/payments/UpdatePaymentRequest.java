package com.turkcell.rentacar.business.dtos.requests.payments;

import com.turkcell.rentacar.entities.enums.PaymentState;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePaymentRequest {
    @NotNull
    private PaymentState state;

    @NotNull
    private LocalDateTime paymentDate;

    @NotNull
    private int rentalId;

    @NotNull
    private double amount;
}
