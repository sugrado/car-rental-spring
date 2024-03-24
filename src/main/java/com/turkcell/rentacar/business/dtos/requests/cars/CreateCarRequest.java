package com.turkcell.rentacar.business.dtos.requests.cars;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {
    @NotNull
    private int modelYear;

    @NotNull
    @Size(max = 9)
    private String plate;

    @NotNull
    private double dailyPrice;

    @NotNull
    private int modelId;

    @NotNull
    private int minFindeksScore;;
}
