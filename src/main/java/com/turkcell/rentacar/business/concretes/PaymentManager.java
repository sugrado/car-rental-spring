package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.adapters.pos.PosService;
import com.turkcell.rentacar.business.abstracts.PaymentService;
import com.turkcell.rentacar.business.dtos.requests.payments.*;
import com.turkcell.rentacar.business.dtos.requests.pos.PaymentRequest;
import com.turkcell.rentacar.business.dtos.responses.payments.*;
import com.turkcell.rentacar.business.rules.PaymentBusinessRules;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.PaymentRepository;
import com.turkcell.rentacar.entities.concretes.Payment;
import com.turkcell.rentacar.entities.enums.PaymentState;
import lombok.AllArgsConstructor;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PaymentManager implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final ModelMapperService modelMapperService;
    private final PosService posService;
    private final PaymentBusinessRules paymentBusinessRules;

    @Override
    public CreatedPaymentResponse add(CreatePaymentRequest createPaymentRequest) {
        PaymentRequest paymentRequest = modelMapperService.forRequest().map(createPaymentRequest.getCreditCard(), PaymentRequest.class);
        paymentRequest.setAmount(createPaymentRequest.getAmount());
        boolean paymentResult = posService.pay(paymentRequest);

        Payment payment = modelMapperService.forRequest().map(createPaymentRequest, Payment.class);
        payment.setState(paymentResult ? PaymentState.SUCCESS : PaymentState.FAILED);
        payment.setPaymentDate(LocalDateTime.now());

        paymentRepository.save(payment);
        return modelMapperService.forResponse().map(payment, CreatedPaymentResponse.class);
    }

    @Override
    public void addPendingPayment(CreatePendingPaymentRequest createPendingPaymentRequest) {
        Payment payment = modelMapperService.forRequest().map(createPendingPaymentRequest, Payment.class);
        payment.setState(PaymentState.PENDING);
        paymentRepository.save(payment);
    }

    @Override
    public UpdatedPaymentResponse update(int id, UpdatePaymentRequest updatePaymentRequest) {
        paymentBusinessRules.paymentIdShouldBeExist(id);
        Payment paymentToUpdate = modelMapperService.forRequest().map(updatePaymentRequest, Payment.class);
        paymentToUpdate.setId(id);
        Payment updatedPayment = paymentRepository.save(paymentToUpdate);
        return modelMapperService.forResponse().map(updatedPayment, UpdatedPaymentResponse.class);
    }

    @Override
    public void delete(int id) {
        Optional<Payment> foundOptionalPayment = paymentRepository.findById(id);
        paymentBusinessRules.paymentShouldBeExist(foundOptionalPayment);
        paymentRepository.delete(foundOptionalPayment.get());
    }

    @Override
    public List<GetAllPaymentsListItemDto> getAll() {
        List<Payment> payments = paymentRepository.findAll();
        return modelMapperService.forResponse().map(payments, new TypeToken<List<GetAllPaymentsListItemDto>>() {
        }.getType());
    }

    @Override
    public List<GetAllPaymentsByRentalListItemDto> getAllByRental(int rentalId) {
        List<Payment> payments = paymentRepository.findByRentalId(rentalId);
        return modelMapperService.forResponse().map(payments, new TypeToken<List<GetAllPaymentsByRentalListItemDto>>() {
        }.getType());
    }

    @Override
    public GetPaymentResponse get(int id) {
        Optional<Payment> foundOptionalPayment = paymentRepository.findById(id);
        paymentBusinessRules.paymentShouldBeExist(foundOptionalPayment);
        return modelMapperService.forResponse().map(foundOptionalPayment.get(), GetPaymentResponse.class);
    }

    @Override
    public void completeWithCard(CompletePaymentWithCardRequest completePaymentWithCardRequest) {
        Optional<Payment> foundOptionalPayment = paymentRepository.findById(completePaymentWithCardRequest.getId());
        paymentBusinessRules.paymentShouldBeExist(foundOptionalPayment);
        Payment payment = foundOptionalPayment.get();

        PaymentRequest paymentRequest = modelMapperService.forRequest().map(completePaymentWithCardRequest.getCreditCardDto(), PaymentRequest.class);
        paymentRequest.setAmount(payment.getAmount());
        boolean paymentResult = posService.pay(paymentRequest);
        paymentBusinessRules.paymentShouldBeSuccess(paymentResult ? PaymentState.SUCCESS : PaymentState.FAILED);

        payment.setState(PaymentState.SUCCESS);
        paymentRepository.save(payment);
    }

    @Override
    public void complete(CompletePaymentRequest completePaymentRequest) {
        Optional<Payment> foundOptionalPayment = paymentRepository.findById(completePaymentRequest.getId());
        paymentBusinessRules.paymentShouldBeExist(foundOptionalPayment);
        Payment payment = foundOptionalPayment.get();

        payment.setState(PaymentState.SUCCESS);
        paymentRepository.save(payment);
    }
}
