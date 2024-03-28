package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.payments.CreatePaymentRequest;
import com.turkcell.rentacar.business.dtos.responses.payments.CreatedPaymentResponse;

public interface PaymentService {
    CreatedPaymentResponse add(CreatePaymentRequest createPaymentRequest);
}
