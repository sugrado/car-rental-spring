package com.turkcell.rentacar.business.dtos.requests.cars;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarRequest {
    @NotNull
    @Size(min = 1800, max = 2025)
    private int modelYear;

    @NotNull
    @Size(max = 9)
    private String plate;

    @NotNull
    private double dailyPrice;

    @NotNull
    private int modelId;
}
