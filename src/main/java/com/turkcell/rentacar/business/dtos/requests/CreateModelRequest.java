package com.turkcell.rentacar.business.dtos.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateModelRequest {
    @NotNull
    @Size(min = 2, max = 30)
    private String name;
    @NotNull
    @Size(min = 0, max = 3000)
    private Double dailyPrice;
    @NotNull
    private int brandId;
    @NotNull
    private int fuelId;
    @NotNull
    private int transmissionId;
}
