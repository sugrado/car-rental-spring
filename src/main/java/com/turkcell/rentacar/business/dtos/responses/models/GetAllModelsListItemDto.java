package com.turkcell.rentacar.business.dtos.responses.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllModelsListItemDto {
    private int id;
    private String name;
    private int brandId;
    private int fuelId;
    private int transmissionId;
    private LocalDateTime createdDate;
}
