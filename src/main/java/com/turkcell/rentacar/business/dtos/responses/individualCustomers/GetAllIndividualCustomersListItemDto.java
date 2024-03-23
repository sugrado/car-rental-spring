package com.turkcell.rentacar.business.dtos.responses.individualCustomers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllIndividualCustomersListItemDto {
    private int id;
    private int customerId;
    private String firstName;
    private String lastName;
    private String identityNumber;
    private LocalDateTime createdDate;
}
