package com.turkcell.rentacar.business.dtos.requests.rentals;

import com.turkcell.rentacar.business.dtos.requests.payments.CreditCardDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {
    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;

    @NotNull
    private int carId;

    @NotNull
    private int customerId;

    @NotNull
    private CreditCardDto creditCard;
}
