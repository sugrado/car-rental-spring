package com.turkcell.rentacar.business.dtos.requests.corporateCustomers;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCorporateCustomerRequest {
    @NotNull
    @Size(min = 2, max = 100)
    private String companyName;

    @NotNull
    @Size(min = 10, max = 10)
    private String taxNo;
}
