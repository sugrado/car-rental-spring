package com.turkcell.rentacar.business.dtos.requests.maintenances;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMaintenanceRequest {
    @NotNull
    private LocalDateTime dateSent;

    @NotNull
    private int carId;
}
