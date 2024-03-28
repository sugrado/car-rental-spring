package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.PaymentService;
import com.turkcell.rentacar.business.dtos.requests.payments.CompletePaymentRequest;
import com.turkcell.rentacar.business.dtos.requests.payments.CompletePaymentWithCardRequest;
import com.turkcell.rentacar.business.dtos.requests.payments.CreatePaymentRequest;
import com.turkcell.rentacar.business.dtos.requests.payments.UpdatePaymentRequest;
import com.turkcell.rentacar.business.dtos.responses.payments.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/payments")
public class PaymentsController {
    private PaymentService paymentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedPaymentResponse add(@Valid @RequestBody CreatePaymentRequest createPaymentRequest) {
        return paymentService.add(createPaymentRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedPaymentResponse update(@PathVariable int id, @Valid @RequestBody UpdatePaymentRequest payment) {
        return paymentService.update(id, payment);
    }

    @PatchMapping("complete-with-card")
    @ResponseStatus(HttpStatus.OK)
    public void completeWithCard(@Valid @RequestBody CompletePaymentWithCardRequest completePaymentWithCardRequest) {
        paymentService.completeWithCard(completePaymentWithCardRequest);
    }

    @PatchMapping("complete")
    @ResponseStatus(HttpStatus.OK)
    public void complete(@Valid @RequestBody CompletePaymentRequest completePaymentRequest) {
        paymentService.complete(completePaymentRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        paymentService.delete(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetPaymentResponse get(@PathVariable int id) {
        return paymentService.get(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllPaymentsListItemDto> getAll() {
        return paymentService.getAll();
    }

    @GetMapping("with-rental/{rentalId}")
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllPaymentsByRentalListItemDto> getAll(@PathVariable int rentalId) {
        return paymentService.getAllByRental(rentalId);
    }
}
