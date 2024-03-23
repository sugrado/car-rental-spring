package com.turkcell.rentacar.business.rules;

import com.turkcell.rentacar.business.messages.ModelMessages;
import com.turkcell.rentacar.core.utilities.exceptions.types.BusinessException;
import com.turkcell.rentacar.dataAccess.abstracts.BrandRepository;
import com.turkcell.rentacar.dataAccess.abstracts.FuelRepository;
import com.turkcell.rentacar.dataAccess.abstracts.ModelRepository;
import com.turkcell.rentacar.dataAccess.abstracts.TransmissionRepository;
import com.turkcell.rentacar.entities.concretes.Brand;
import com.turkcell.rentacar.entities.concretes.Fuel;
import com.turkcell.rentacar.entities.concretes.Model;
import com.turkcell.rentacar.entities.concretes.Transmission;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class ModelBusinessRules {
    private final BrandRepository brandRepository;
    private final FuelRepository fuelRepository;
    private final TransmissionRepository transmissionRepository;
    private final ModelRepository modelRepository;

    public void modelShouldBeExist(Optional<Model> model) {
        if (model.isEmpty()) {
            throw new BusinessException(ModelMessages.modelNotFound);
        }
    }

    public void modelIdShouldBeExist(int modelId) {
        Optional<Model> model = modelRepository.findById(modelId);
        if (model.isEmpty()) {
            throw new BusinessException(ModelMessages.modelNotFound);
        }
    }

    public void brandIdShouldBeExist(int brandId) {
        Optional<Brand> brand = brandRepository.findById(brandId);
        if (brand.isEmpty()) {
            throw new BusinessException(ModelMessages.brandNotFound);
        }
    }

    public void fuelIdShouldBeExist(int fuelId) {
        Optional<Fuel> fuel = fuelRepository.findById(fuelId);
        if (fuel.isEmpty()) {
            throw new BusinessException(ModelMessages.fuelNotFound);
        }
    }

    public void transmissionIdShouldBeExist(int transmissionId) {
        Optional<Transmission> transmission = transmissionRepository.findById(transmissionId);
        if (transmission.isEmpty()) {
            throw new BusinessException(ModelMessages.transmissionNotFound);
        }
    }
}
