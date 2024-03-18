package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.FuelService;
import com.turkcell.rentacar.business.dtos.requests.CreateFuelRequest;
import com.turkcell.rentacar.business.dtos.requests.UpdateFuelRequest;
import com.turkcell.rentacar.business.dtos.responses.*;
import com.turkcell.rentacar.business.dtos.responses.CreatedFuelResponse;
import com.turkcell.rentacar.business.dtos.responses.UpdatedFuelResponse;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.FuelRepository;
import com.turkcell.rentacar.entities.concretes.Fuel;
import com.turkcell.rentacar.entities.concretes.Fuel;
import com.turkcell.rentacar.entities.concretes.Fuel;
import com.turkcell.rentacar.entities.concretes.Fuel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class FuelManager implements FuelService {
    FuelRepository fuelRepository; // IoC
    private static final String fuelNotFoundMessage = "Fuel not found";
    private static final String fuelAlreadyExistsMessage = "Fuel already exists";
    private ModelMapperService modelMapperService;

    @Override
    public CreatedFuelResponse add(CreateFuelRequest createFuelRequest) {
        fuelNameCanNotBeDuplicatedWhenInserted(createFuelRequest.getName());

        Fuel fuel = modelMapperService.forRequest().map(createFuelRequest, Fuel.class);
        fuel.setCreatedDate(LocalDateTime.now());

        Fuel createdFuel = fuelRepository.save(fuel);
        return modelMapperService.forResponse().map(createdFuel, CreatedFuelResponse.class);
    }

    @Override
    public UpdatedFuelResponse update(int id, UpdateFuelRequest updateFuelRequest) {
        Optional<Fuel> foundOptionalFuel = fuelRepository.findById(id);
        fuelShouldBeExist(foundOptionalFuel);
        fuelNameCanNotBeDuplicatedWhenUpdated(id, updateFuelRequest.getName());

        Fuel fuelToUpdate = foundOptionalFuel.get();
        modelMapperService.forRequest().map(updateFuelRequest, fuelToUpdate);
        fuelToUpdate.setUpdatedDate(LocalDateTime.now());

        Fuel updatedFuel = fuelRepository.save(fuelToUpdate);
        return modelMapperService.forResponse().map(updatedFuel, UpdatedFuelResponse.class);
    }

    @Override
    public void delete(int id) {
        Optional<Fuel> foundOptionalFuel = fuelRepository.findById(id);
        fuelShouldBeExist(foundOptionalFuel);
        fuelRepository.delete(foundOptionalFuel.get());
    }

    @Override
    public List<Fuel> getAll() {
        return fuelRepository.findAll();
    }

    @Override
    public GetFuelResponse get(int id) {
        Optional<Fuel> foundOptionalFuel = fuelRepository.findById(id);
        fuelShouldBeExist(foundOptionalFuel);
        return modelMapperService.forResponse().map(foundOptionalFuel.get(), GetFuelResponse.class);
    }

    // temp business rules
    // TODO: FuelBusinessRules
    private void fuelShouldBeExist(Optional<Fuel> fuel) {
        if (fuel.isEmpty()) {
            throw new RuntimeException(fuelNotFoundMessage);
        }
    }

    private void fuelNameCanNotBeDuplicatedWhenInserted(String name) {
        Optional<Fuel> foundOptionalFuel = fuelRepository.getByNameIgnoreCase(name.trim());
        if (foundOptionalFuel.isPresent()) {
            throw new RuntimeException(fuelAlreadyExistsMessage);
        }
    }

    private void fuelNameCanNotBeDuplicatedWhenUpdated(int id, String name) {
        boolean exists = fuelRepository.existsByNameIgnoreCaseAndIdIsNot(name.trim(), id);
        if (exists) {
            throw new RuntimeException(fuelAlreadyExistsMessage);
        }
    }
}
