package com.turkcell.rentacar.business.rules;

import com.turkcell.rentacar.business.constants.messages.CarMessages;
import com.turkcell.rentacar.core.utilities.exceptions.types.BusinessException;
import com.turkcell.rentacar.dataAccess.abstracts.CarRepository;
import com.turkcell.rentacar.entities.concretes.Car;
import com.turkcell.rentacar.entities.enums.CarState;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CarBusinessRules {
    private final CarRepository carRepository;

    public void carShouldBeExist(Optional<Car> car) {
        if (car.isEmpty()) {
            throw new BusinessException(CarMessages.carNotFound);
        }
    }

    public void carIdShouldBeExist(int carId) {
        Optional<Car> car = carRepository.findById(carId);
        if (car.isEmpty()) {
            throw new BusinessException(CarMessages.carNotFound);
        }
    }

    public void carShouldBeAvailable(int carId) {
        Optional<Car> car = carRepository.findById(carId);
        if (!car.get().getState().equals(CarState.AVAILABLE)) {
            throw new BusinessException(CarMessages.carIsNotAvailable);
        }
    }
}
