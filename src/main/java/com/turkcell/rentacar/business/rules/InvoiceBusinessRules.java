package com.turkcell.rentacar.business.rules;

import com.turkcell.rentacar.business.constants.messages.InvoiceMessages;
import com.turkcell.rentacar.core.utilities.exceptions.types.BusinessException;
import com.turkcell.rentacar.dataAccess.abstracts.InvoiceRepository;
import com.turkcell.rentacar.entities.concretes.Invoice;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class InvoiceBusinessRules {
    private final InvoiceRepository invoiceRepository;

    public void invoiceShouldBeExist(Optional<Invoice> invoice) {
        if (invoice.isEmpty()) {
            throw new BusinessException(InvoiceMessages.invoiceNotFound);
        }
    }


}
