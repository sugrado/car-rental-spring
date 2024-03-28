package com.turkcell.rentacar.business.dtos.requests.payments;

import lombok.Data;

@Data
public class CompletePaymentWithCardRequest {
    private int id;
    private CreditCardDto creditCardDto;
}
