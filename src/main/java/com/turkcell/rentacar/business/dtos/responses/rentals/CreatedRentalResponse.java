package com.turkcell.rentacar.business.dtos.responses.rentals;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatedRentalResponse {
    private int id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime returnDate;
    private int carId;
    private int customerId;
    private LocalDateTime createdDate;
}
