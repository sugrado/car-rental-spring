package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.adapters.pos.PosService;
import com.turkcell.rentacar.business.abstracts.CarService;
import com.turkcell.rentacar.business.abstracts.InvoiceService;
import com.turkcell.rentacar.business.dtos.requests.pos.PaymentRequest;
import com.turkcell.rentacar.business.dtos.responses.invoices.GetAllInvoicesListItemDto;
import com.turkcell.rentacar.business.dtos.responses.invoices.GetInvoiceResponse;
import com.turkcell.rentacar.business.rules.InvoiceBusinessRules;
import com.turkcell.rentacar.core.utilities.helpers.DateHelper;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.InvoiceRepository;
import com.turkcell.rentacar.entities.concretes.Invoice;
import com.turkcell.rentacar.entities.concretes.Rental;
import lombok.AllArgsConstructor;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class InvoiceManager implements InvoiceService {
//    private final InvoiceRepository invoiceRepository;
//    private final ModelMapperService modelMapperService;
//    private final InvoiceBusinessRules invoiceBusinessRules;
//    private final CreditCardService creditCardService;
//    private final PosService posService;
//    private final CarService carService;
//
//    @Override
//    public Invoice add(Rental rental, int creditCardId) {
//        short totalDay = DateHelper.totalDaysBetween(rental.getStartDate(), rental.getEndDate());
//        double totalPrice = carService.calculatePriceByDays(rental.getCar().getId(), totalDay);
//
//        CreditCard creditCard = creditCardService.getRecord(creditCardId);
//        PaymentRequest paymentRequest = createPaymentRequest(creditCard, totalPrice);
//        boolean paymentSuccess = posService.pay(paymentRequest);
//        invoiceBusinessRules.paymentShouldBeSuccess(paymentSuccess);
//
//        Invoice invoice = Invoice.builder()
//                .rental(rental)
//                .totalAmount(totalPrice)
//                .totalDay(totalDay)
//                .build();
//
//        return invoiceRepository.save(invoice);
//    }
//
//    private PaymentRequest createPaymentRequest(CreditCard creditCard, double totalPrice) {
//        PaymentRequest paymentRequest = modelMapperService.forRequest().map(creditCard, PaymentRequest.class);
//        paymentRequest.setAmount(totalPrice);
//        return paymentRequest;
//    }
//
//    @Override
//    public GetInvoiceResponse get(int id) {
//        Optional<Invoice> foundOptionalInvoice = invoiceRepository.findById(id);
//        invoiceBusinessRules.invoiceShouldBeExist(foundOptionalInvoice);
//        return modelMapperService.forResponse().map(foundOptionalInvoice.get(), GetInvoiceResponse.class);
//    }
//
//    @Override
//    public List<GetAllInvoicesListItemDto> getAll() {
//        List<Invoice> invoices = invoiceRepository.findAll();
//        return modelMapperService.forResponse().map(invoices, new TypeToken<List<GetAllInvoicesListItemDto>>() {
//        }.getType());
//    }
}
