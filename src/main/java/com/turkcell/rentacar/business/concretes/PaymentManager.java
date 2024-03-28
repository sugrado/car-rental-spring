package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.adapters.pos.PosService;
import com.turkcell.rentacar.business.abstracts.PaymentService;
import com.turkcell.rentacar.business.dtos.requests.payments.CreatePaymentRequest;
import com.turkcell.rentacar.business.dtos.requests.pos.PaymentRequest;
import com.turkcell.rentacar.business.dtos.responses.payments.CreatedPaymentResponse;
import com.turkcell.rentacar.business.rules.PaymentBusinessRules;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.PaymentRepository;
import com.turkcell.rentacar.entities.concretes.Payment;
import com.turkcell.rentacar.entities.enums.PaymentState;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class PaymentManager implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final ModelMapperService modelMapperService;
    private final PosService posService;
    private final PaymentBusinessRules paymentBusinessRules;

    @Override
    public CreatedPaymentResponse add(CreatePaymentRequest createPaymentRequest) {
        boolean paymentResult = posService.pay(modelMapperService.forRequest().map(createPaymentRequest, PaymentRequest.class));
        paymentBusinessRules.paymentShouldBeSuccess(paymentResult);

        Payment payment = modelMapperService.forRequest().map(createPaymentRequest, Payment.class);
        payment.setState(PaymentState.SUCCESS); // TODO:
        payment.setPaymentDate(LocalDateTime.now());
        paymentRepository.save(payment);
        return modelMapperService.forResponse().map(payment, CreatedPaymentResponse.class);
    }
}
