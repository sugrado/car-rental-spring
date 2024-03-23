package com.turkcell.rentacar.business.dtos.responses.corporateCustomers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCorporateCustomersListItemDto {
    private int id;
    private int customerId;
    private String companyName;
    private String taxNo;
    private LocalDateTime createdDate;
}
