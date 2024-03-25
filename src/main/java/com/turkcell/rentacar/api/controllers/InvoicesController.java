package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/invoices")
public class InvoicesController {
    private final InvoiceService invoiceService;
    // TODO: doldurulacak
}
