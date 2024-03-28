package com.turkcell.rentacar.business.dtos.responses.payments;

import com.turkcell.rentacar.entities.enums.PaymentState;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetAllPaymentsByRentalListItemDto {
    private PaymentState state;
    private double amount;
    private LocalDateTime paymentDate;
}
