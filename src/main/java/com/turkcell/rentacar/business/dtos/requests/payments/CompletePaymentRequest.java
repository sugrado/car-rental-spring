package com.turkcell.rentacar.business.dtos.requests.payments;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CompletePaymentRequest {
    @NotNull
    private int id;

    @NotNull
    private LocalDateTime paymentDate;
}
