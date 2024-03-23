package com.turkcell.rentacar.business.dtos.responses.brands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatedBrandResponse {
    private int id;
    private String name;
    private LocalDateTime createdDate;
}
