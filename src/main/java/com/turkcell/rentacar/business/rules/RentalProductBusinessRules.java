package com.turkcell.rentacar.business.rules;

import com.turkcell.rentacar.business.constants.messages.RentalProductMessages;
import com.turkcell.rentacar.core.utilities.exceptions.types.BusinessException;
import com.turkcell.rentacar.dataAccess.abstracts.RentalRepository;
import com.turkcell.rentacar.entities.concretes.Rental;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RentalProductBusinessRules {
    private final RentalRepository rentalRepository;

    public void rentalEndDateShouldNotBePassed(int rentalId) {
        Rental rental = rentalRepository.findById(rentalId).get();
        if (rental.getEndDate().isBefore(LocalDateTime.now())) {
            throw new BusinessException(RentalProductMessages.RENTAL_END_DATE_SHOULD_NOT_BE_PASSED);
        }
    }
}
