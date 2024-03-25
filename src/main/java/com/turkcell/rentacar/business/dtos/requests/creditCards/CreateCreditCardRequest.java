package com.turkcell.rentacar.business.dtos.requests.creditCards;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCreditCardRequest {
    @NotNull
    @Size(min = 2, max = 16)
    private String name;

    @NotNull
    @Size(min = 16, max = 16)
    private String cardNumber;

    @NotNull
    @Size(min = 4, max = 64)
    private String cardHolder;

    @NotNull
    @Min(100)
    @Max(128)
    private short cvv;

    @NotNull
    @Min(1)
    @Max(12)
    private byte expirationMonth;

    @NotNull
//    @ValidExpirationYear
    private short expirationYear;

    @NotNull
    private int customerId;
}
