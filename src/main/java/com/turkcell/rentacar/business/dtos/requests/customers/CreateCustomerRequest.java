package com.turkcell.rentacar.business.dtos.requests.customers;

import com.turkcell.rentacar.entities.enums.CustomerType;
import jakarta.validation.constraints.NotNull;;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerRequest {
    @NotNull
    private CustomerType type;
}
