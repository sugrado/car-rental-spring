package com.turkcell.rentacar.business.dtos.responses.invoices;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllInvoicesListItemDto {
    private int id;
    private double totalAmount;
    private short totalDay;
    private LocalDateTime rentalStartDate;
    private LocalDateTime rentalEndDate;
    private LocalDateTime rentalReturnDate;
    private LocalDateTime createdDate;
}
