package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.ModelService;
import com.turkcell.rentacar.business.dtos.requests.models.CreateModelRequest;
import com.turkcell.rentacar.business.dtos.requests.models.UpdateModelRequest;
import com.turkcell.rentacar.business.dtos.responses.models.CreatedModelResponse;
import com.turkcell.rentacar.business.dtos.responses.models.GetAllModelsListItemDto;
import com.turkcell.rentacar.business.dtos.responses.models.GetModelResponse;
import com.turkcell.rentacar.business.dtos.responses.models.UpdatedModelResponse;
import com.turkcell.rentacar.business.rules.BrandBusinessRules;
import com.turkcell.rentacar.business.rules.FuelBusinessRules;
import com.turkcell.rentacar.business.rules.ModelBusinessRules;
import com.turkcell.rentacar.business.rules.TransmissionBusinessRules;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.ModelRepository;
import com.turkcell.rentacar.entities.concretes.Model;
import lombok.AllArgsConstructor;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ModelManager implements ModelService {
    private final ModelRepository modelRepository;
    private final ModelBusinessRules modelBusinessRules;
    private final BrandBusinessRules brandBusinessRules;
    private final FuelBusinessRules fuelBusinessRules;
    private final TransmissionBusinessRules transmissionBusinessRules;
    private final ModelMapperService modelMapperService;

    @Override
    public CreatedModelResponse add(CreateModelRequest createModelRequest) {
        brandBusinessRules.brandIdShouldBeExist(createModelRequest.getBrandId());
        fuelBusinessRules.fuelIdShouldBeExist(createModelRequest.getFuelId());
        transmissionBusinessRules.transmissionIdShouldBeExist(createModelRequest.getTransmissionId());

        Model model = modelMapperService.forRequest().map(createModelRequest, Model.class);

        Model createdModel = modelRepository.save(model);
        return modelMapperService.forResponse().map(createdModel, CreatedModelResponse.class);
    }

    @Override
    public UpdatedModelResponse update(int id, UpdateModelRequest updateModelRequest) {
        modelBusinessRules.modelIdShouldBeExist(id);
        brandBusinessRules.brandIdShouldBeExist(updateModelRequest.getBrandId());
        fuelBusinessRules.fuelIdShouldBeExist(updateModelRequest.getFuelId());
        transmissionBusinessRules.transmissionIdShouldBeExist(updateModelRequest.getTransmissionId());

        Model modelToUpdate = modelMapperService.forRequest().map(updateModelRequest, Model.class);
        modelToUpdate.setId(id);
        Model updatedModel = modelRepository.save(modelToUpdate);
        return modelMapperService.forResponse().map(updatedModel, UpdatedModelResponse.class);
    }

    @Override
    public void delete(int id) {
        Optional<Model> foundOptionalModel = modelRepository.findById(id);
        modelBusinessRules.modelShouldBeExist(foundOptionalModel);
        modelRepository.delete(foundOptionalModel.get());
    }

    @Override
    public List<GetAllModelsListItemDto> getAll() {
        List<Model> models = modelRepository.findAll();
        return modelMapperService.forResponse().map(models, new TypeToken<List<GetAllModelsListItemDto>>() {
        }.getType());
    }

    @Override
    public GetModelResponse get(int id) {
        Optional<Model> foundOptionalModel = modelRepository.findById(id);
        modelBusinessRules.modelShouldBeExist(foundOptionalModel);
        return modelMapperService.forResponse().map(foundOptionalModel.get(), GetModelResponse.class);
    }
}
