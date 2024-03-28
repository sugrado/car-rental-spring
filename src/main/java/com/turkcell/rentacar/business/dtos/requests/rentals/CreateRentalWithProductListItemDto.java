package com.turkcell.rentacar.business.dtos.requests.rentals;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateRentalWithProductListItemDto {
    @NotNull
    @Min(0)
    private short quantity;

    @NotNull
    private int productId;
}
