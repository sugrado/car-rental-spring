package com.turkcell.rentacar.business.dtos.requests.cars;

import com.turkcell.rentacar.business.constants.regexes.CarRegexes;
import jakarta.validation.constraints.*;
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
    @Pattern(regexp = CarRegexes.plate)
    private String plate;

    @NotNull
    @DecimalMin("100.0")
    @DecimalMax("10000.0")
    private double dailyPrice;

    @NotNull
    private int modelId;

    @NotNull
    @Min(1)
    @Max(1900)
    private int minFindeksScore;
}
