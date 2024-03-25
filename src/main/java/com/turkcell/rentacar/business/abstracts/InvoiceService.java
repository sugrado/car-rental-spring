package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.responses.invoices.GetAllInvoicesListItemDto;
import com.turkcell.rentacar.business.dtos.responses.invoices.GetInvoiceResponse;
import com.turkcell.rentacar.entities.concretes.Invoice;
import com.turkcell.rentacar.entities.concretes.Rental;

import java.util.List;

public interface InvoiceService {
    Invoice add(Rental rental, int creditCardId);

    GetInvoiceResponse get(int id);

    List<GetAllInvoicesListItemDto> getAll();
}
