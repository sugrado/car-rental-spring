package com.turkcell.rentacar.business.dtos.responses.maintenances;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatedMaintenanceResponse {
    private int id;
    private int carId;
    private LocalDateTime sentDate;
    private LocalDateTime expectedReturnDate;
    private LocalDateTime actualReturnDate;
    private LocalDateTime createdDate;
}
