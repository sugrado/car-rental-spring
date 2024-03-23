package com.turkcell.rentacar.business.dtos.responses.transmissions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatedTransmissionResponse {
    private int id;
    private String name;
    private LocalDateTime createdDate;
}
