package com.turkcell.rentacar.business.rules;

import com.turkcell.rentacar.dataAccess.abstracts.FuelRepository;
import com.turkcell.rentacar.entities.concretes.Fuel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class FuelBusinessRules {

    private final FuelRepository fuelRepository;
    private static final String fuelNotFoundMessage = "Fuel not found";
    private static final String fuelAlreadyExistsMessage = "Fuel already exists";

    public void fuelShouldBeExist(Optional<Fuel> fuel) {
        if (fuel.isEmpty()) {
            throw new RuntimeException(fuelNotFoundMessage);
        }
    }

    public void fuelNameCanNotBeDuplicatedWhenInserted(String name) {
        Optional<Fuel> foundOptionalFuel = fuelRepository.getByNameIgnoreCase(name.trim());
        if (foundOptionalFuel.isPresent()) {
            throw new RuntimeException(fuelAlreadyExistsMessage);
        }
    }

    public void fuelNameCanNotBeDuplicatedWhenUpdated(int id, String name) {
        boolean exists = fuelRepository.existsByNameIgnoreCaseAndIdIsNot(name.trim(), id);
        if (exists) {
            throw new RuntimeException(fuelAlreadyExistsMessage);
        }
    }
}
