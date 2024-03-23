package com.turkcell.rentacar.business.dtos.responses.maintenances;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetMaintenanceResponse {
    private int id;
    private LocalDateTime dateSent;
    private LocalDateTime dateReturned;
    private int carId;
    private LocalDateTime createdDate;
}
