package com.turkcell.rentacar.business.dtos.responses.customers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCustomersListItemDto {
    private int id;
    private LocalDateTime createdDate;
}
