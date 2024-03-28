package com.turkcell.rentacar.business.dtos.responses.products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetProductResponse {
    private int id;
    private String name;
    private double dailyPrice;
    private LocalDateTime createdDate;
}
