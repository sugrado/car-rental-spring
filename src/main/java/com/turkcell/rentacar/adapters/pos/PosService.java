package com.turkcell.rentacar.adapters.pos;

import com.turkcell.rentacar.business.dtos.requests.pos.PaymentRequest;

public interface PosService {
    boolean pay(PaymentRequest paymentRequest);
}
