package com.turkcell.rentacar.business.rules;

import com.turkcell.rentacar.business.constants.messages.InvoiceMessages;
import com.turkcell.rentacar.core.utilities.exceptions.types.BusinessException;
import org.springframework.stereotype.Service;

@Service
public class PaymentBusinessRules {
    public void paymentShouldBeSuccess(boolean success) {
        if (!success) {
            throw new BusinessException(InvoiceMessages.paymentFailed);
        }
    }
}
