package com.turkcell.rentacar.business.dtos.requests.rentals;

import com.turkcell.rentacar.business.dtos.requests.payments.CreditCardDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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
    private List<CreateRentalWithProductListItemDto> products;

    @NotNull
    private CreditCardDto creditCard;
}
