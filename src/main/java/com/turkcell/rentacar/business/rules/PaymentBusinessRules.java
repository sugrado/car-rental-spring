package com.turkcell.rentacar.business.rules;

import com.turkcell.rentacar.business.constants.messages.InvoiceMessages;
import com.turkcell.rentacar.business.constants.messages.PaymentMessages;
import com.turkcell.rentacar.business.dtos.responses.payments.CreatedPaymentResponse;
import com.turkcell.rentacar.core.utilities.exceptions.types.BusinessException;
import com.turkcell.rentacar.dataAccess.abstracts.PaymentRepository;
import com.turkcell.rentacar.entities.concretes.Payment;
import com.turkcell.rentacar.entities.enums.PaymentState;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PaymentBusinessRules {
    private final PaymentRepository paymentRepository;

    public void paymentShouldBeSuccess(CreatedPaymentResponse createdPaymentResponse) {
        if (!createdPaymentResponse.getState().equals(PaymentState.SUCCESS)) {
            throw new BusinessException(InvoiceMessages.paymentFailed);
        }
    }

    public void paymentShouldBeExist(Optional<Payment> payment) {
        if (payment.isEmpty()) {
            throw new BusinessException(PaymentMessages.PAYMENT_NOT_FOUND);
        }
    }

    public void paymentIdShouldBeExist(int paymentId) {
        Optional<Payment> payment = paymentRepository.findById(paymentId);
        if (payment.isEmpty()) {
            throw new BusinessException(PaymentMessages.PAYMENT_ALREADY_EXISTS);
        }
    }
}
