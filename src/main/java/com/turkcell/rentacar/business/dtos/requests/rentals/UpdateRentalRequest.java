package com.turkcell.rentacar.business.dtos.requests.rentals;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalRequest {
    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;

    private LocalDateTime returnDate;

    @NotNull
    private int carId;

    @NotNull
    private int customerId;
}
