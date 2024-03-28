package com.turkcell.rentacar.business.dtos.requests.rentalProducts;

import com.turkcell.rentacar.business.dtos.requests.payments.CreditCardDto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CreateRentalProductRequest {
    @NotNull
    private CreditCardDto creditCard;

    @NotNull
    private List<CreateRentalProductListItemDto> rentalProductList;
}


