package com.turkcell.rentacar.business.rules;

import com.turkcell.rentacar.business.abstracts.BrandService;
import com.turkcell.rentacar.business.abstracts.FuelService;
import com.turkcell.rentacar.business.abstracts.TransmissionService;
import com.turkcell.rentacar.dataAccess.abstracts.ModelRepository;
import com.turkcell.rentacar.entities.concretes.Model;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class ModelBusinessRules {
    private static final String modelNotFoundMessage = "Model not found";
    private final BrandService brandService;
    private final FuelService fuelService;
    private final TransmissionService transmissionService;
    private final ModelRepository modelRepository;

    public void modelShouldBeExist(Optional<Model> model) {
        if (model.isEmpty()) {
            throw new RuntimeException(modelNotFoundMessage);
        }
    }

    public void modelIdShouldBeExist(int modelId) {
        Optional<Model> model = modelRepository.findById(modelId);
        if (model.isEmpty()) {
            throw new RuntimeException(modelNotFoundMessage);
        }
    }

    public void brandIdShouldBeExist(int brandId) {
        brandService.get(brandId);
    }

    public void fuelIdShouldBeExist(int fuelId) {
        fuelService.get(fuelId);
    }

    public void transmissionIdShouldBeExist(int transmissionId) {
        transmissionService.get(transmissionId);
    }
}
