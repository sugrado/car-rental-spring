package com.turkcell.rentacar.business.rules;

import com.turkcell.rentacar.business.abstracts.CustomerService;
import com.turkcell.rentacar.business.constants.messages.RentalMessages;
import com.turkcell.rentacar.core.utilities.exceptions.types.BusinessException;
import com.turkcell.rentacar.dataAccess.abstracts.CarRepository;
import com.turkcell.rentacar.dataAccess.abstracts.RentalRepository;
import com.turkcell.rentacar.entities.concretes.Car;
import com.turkcell.rentacar.entities.concretes.Rental;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RentalBusinessRules {
    private final RentalRepository rentalRepository;
    private final CarRepository carRepository;
    private final CustomerService customerService;

    public void rentalShouldBeExist(Optional<Rental> rental) {
        if (rental.isEmpty()) {
            throw new BusinessException(RentalMessages.RENTAL_NOT_FOUND);
        }
    }

    public void rentalShouldNotBeReturned(Rental rental) {
        if (rental.getReturnDate() != null) {
            throw new BusinessException(RentalMessages.RENTAL_ALREADY_RETURNED);
        }
    }

    public void rentalIdShouldBeExist(int rentalId) {
        Optional<Rental> rental = rentalRepository.findById(rentalId);
        if (rental.isEmpty()) {
            throw new BusinessException(RentalMessages.RENTAL_NOT_FOUND);
        }
    }

    public void customerFindeksScoreShouldBeEnough(int customerId, int carId) {
        Optional<Car> car = carRepository.findById(carId);
        if (car.get().getMinFindeksScore() > customerService.getFindeksScore(customerId)) {
            throw new BusinessException(RentalMessages.CUSTOMER_FINDEKS_SCORE_NOT_ENOUGH);
        }
    }
}
