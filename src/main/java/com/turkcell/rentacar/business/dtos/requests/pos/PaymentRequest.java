package com.turkcell.rentacar.business.dtos.requests.pos;

import lombok.Data;

@Data
public class PaymentRequest {
    private String cardNumber;
    private String cardHolder;
    private String cvv;
    private String expirationYear;
    private String expirationMonth;
    private double amount;
}
