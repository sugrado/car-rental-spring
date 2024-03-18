package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.*;
import com.turkcell.rentacar.business.abstracts.ModelService;
import com.turkcell.rentacar.business.dtos.requests.CreateModelRequest;
import com.turkcell.rentacar.business.dtos.requests.UpdateModelRequest;
import com.turkcell.rentacar.business.dtos.responses.CreatedModelResponse;
import com.turkcell.rentacar.business.dtos.responses.GetModelResponse;
import com.turkcell.rentacar.business.dtos.responses.UpdatedModelResponse;
import com.turkcell.rentacar.business.dtos.responses.UpdatedModelResponse;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.ModelRepository;
import com.turkcell.rentacar.entities.concretes.Model;
import com.turkcell.rentacar.entities.concretes.Fuel;
import com.turkcell.rentacar.entities.concretes.Model;
import com.turkcell.rentacar.entities.concretes.Transmission;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ModelManager implements ModelService {
    ModelRepository modelRepository; // IoC
    BrandService brandService;
    FuelService fuelService;
    TransmissionService transmissionService;
    ModelMapperService modelMapperService;

    private static final String modelNotFoundMessage = "Model not found";

    @Override
    public CreatedModelResponse add(CreateModelRequest createModelRequest) {
        brandIdShouldBeExist(createModelRequest.getBrandId());
        fuelIdShouldBeExist(createModelRequest.getFuelId());
        transmissionIdShouldBeExist(createModelRequest.getTransmissionId());

        Model model = modelMapperService.forRequest().map(createModelRequest, Model.class);
        model.setCreatedDate(LocalDateTime.now());

        Model createdModel = modelRepository.save(model);
        return modelMapperService.forResponse().map(createdModel, CreatedModelResponse.class);
    }

    @Override
    public UpdatedModelResponse update(int id, UpdateModelRequest updateModelRequest) {
        Optional<Model> foundOptionalModel = modelRepository.findById(id);
        modelShouldBeExist(foundOptionalModel);

        Model modelToUpdate = foundOptionalModel.get();
        modelMapperService.forRequest().map(updateModelRequest, modelToUpdate);
        modelToUpdate.setUpdatedDate(LocalDateTime.now());

        brandIdShouldBeExist(modelToUpdate.getBrand().getId());
        fuelIdShouldBeExist(modelToUpdate.getFuel().getId());
        transmissionIdShouldBeExist(modelToUpdate.getTransmission().getId());

        Model updatedModel = modelRepository.save(modelToUpdate);
        return modelMapperService.forResponse().map(updatedModel, UpdatedModelResponse.class);
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
    public GetModelResponse get(int id) {
        Optional<Model> foundOptionalModel = modelRepository.findById(id);
        modelShouldBeExist(foundOptionalModel);
        return modelMapperService.forResponse().map(foundOptionalModel.get(), GetModelResponse.class);
    }

    // temp business rules
    // TODO: ModelBusinessRules
    private void modelShouldBeExist(Optional<Model> model) {
        if (model.isEmpty()) {
            throw new RuntimeException(modelNotFoundMessage);
        }
    }

    private void brandIdShouldBeExist(int brandId) {
        brandService.get(brandId);
    }

    private void fuelIdShouldBeExist(int fuelId) {
        fuelService.get(fuelId);
    }

    private void transmissionIdShouldBeExist(int transmissionId) {
        transmissionService.get(transmissionId);
    }
}
