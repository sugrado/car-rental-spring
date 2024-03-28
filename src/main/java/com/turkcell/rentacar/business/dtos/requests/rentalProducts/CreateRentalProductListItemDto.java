package com.turkcell.rentacar.business.dtos.requests.rentalProducts;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateRentalProductListItemDto {
    @NotNull
    @Min(0)
    private short quantity;

    @NotNull
    private int productId;
}
