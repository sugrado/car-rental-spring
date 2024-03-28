package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.payments.*;
import com.turkcell.rentacar.business.dtos.responses.payments.*;

import java.util.List;

public interface PaymentService {
    CreatedPaymentResponse add(CreatePaymentRequest createPaymentRequest);

    void addWithoutPayment(CreateWithoutPaymentRequest createWithoutPaymentRequest);

    UpdatedPaymentResponse update(int id, UpdatePaymentRequest updatePaymentRequest);

    void delete(int id);

    List<GetAllPaymentsListItemDto> getAll();

    List<GetAllPaymentsByRentalListItemDto> getAllByRental(int rentalId);

    GetPaymentResponse get(int id);

    void completeWithCard(CompletePaymentWithCardRequest completePaymentWithCardRequest);

    void complete(CompletePaymentRequest completePaymentRequest);
}
