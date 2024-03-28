package com.turkcell.rentacar.adapters.pos;

import com.turkcell.rentacar.business.dtos.requests.pos.PaymentRequest;
import com.turkcell.rentacar.core.utilities.exceptions.types.BusinessException;
import com.turkcell.rentacar.outServices.FakePosClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FakePosAdapter implements PosService {
    private final FakePosClient fakePosClient;

    @Override
    public boolean pay(PaymentRequest paymentRequest) {
        String expireDate = paymentRequest.getExpirationMonth() + "/" + paymentRequest.getExpirationYear();
        boolean success = fakePosClient.createPayment(paymentRequest.getCardNumber(), paymentRequest.getCardHolder(), paymentRequest.getCvv(), expireDate, paymentRequest.getAmount());
        if (!success) {
            throw new BusinessException("Payment failed");
        }
        return true;
    }
}