package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.BrandService;
import com.turkcell.rentacar.business.abstracts.FuelService;
import com.turkcell.rentacar.business.abstracts.ModelService;
import com.turkcell.rentacar.business.abstracts.TransmissionService;
import com.turkcell.rentacar.dataAccess.abstracts.ModelRepository;
import com.turkcell.rentacar.entities.concretes.Brand;
import com.turkcell.rentacar.entities.concretes.Fuel;
import com.turkcell.rentacar.entities.concretes.Model;
import com.turkcell.rentacar.entities.concretes.Transmission;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ModelManager implements ModelService {
    ModelRepository modelRepository; // IoC
    BrandService brandService;
    FuelService fuelService;
    TransmissionService transmissionService;

    private static final String modelNotFoundMessage = "Model not found";

    @Override
    public Model add(Model model) {
        // TODO: Validation rules
        brandIdShouldBeExist(model.getBrand().getId());
        fuelIdShouldBeExist(model.getFuel().getId());
        transmissionIdShouldBeExist(model.getTransmission().getId());
        return modelRepository.save(model);
    }

    @Override
    public Model update(Model model) {
        // TODO: Validation rules
        Optional<Model> foundOptionalModel = modelRepository.findById(model.getId());
        modelShouldBeExist(foundOptionalModel);

        Model modelToUpdate = foundOptionalModel.get();
        modelToUpdate.setName(model.getName()); // TODO: mapper
        modelToUpdate.setDailyPrice(model.getDailyPrice());
        modelToUpdate.setBrand(model.getBrand());
        modelToUpdate.setFuel(model.getFuel());
        modelToUpdate.setTransmission(model.getTransmission());

        brandIdShouldBeExist(modelToUpdate.getBrand().getId());
        fuelIdShouldBeExist(modelToUpdate.getFuel().getId());
        transmissionIdShouldBeExist(modelToUpdate.getTransmission().getId());

        return modelRepository.save(modelToUpdate);
    }

    @Override
    public void delete(int id) {
        Optional<Model> foundOptionalModel = modelRepository.findById(id);
        modelShouldBeExist(foundOptionalModel);
        modelRepository.delete(foundOptionalModel.get());
    }

    @Override
    public List<Model> getAll() {
        return modelRepository.findAll();
    }

    @Override
    public Model get(int id) {
        Optional<Model> foundOptionalModel = modelRepository.findById(id);
        modelShouldBeExist(foundOptionalModel);
        return foundOptionalModel.get();
    }

    // temp business rules
    // TODO: ModelBusinessRules
    private void modelShouldBeExist(Optional<Model> model) {
        if (model.isEmpty()) {
            throw new RuntimeException(modelNotFoundMessage);
        }
    }

    private void brandIdShouldBeExist(int brandId) {
        Brand brand = brandService.get(brandId);
    }

    private void fuelIdShouldBeExist(int fuelId) {
        Fuel fuel = fuelService.get(fuelId);
    }

    private void transmissionIdShouldBeExist(int transmissionId) {
        Transmission transmission = transmissionService.get(transmissionId);
    }
}
