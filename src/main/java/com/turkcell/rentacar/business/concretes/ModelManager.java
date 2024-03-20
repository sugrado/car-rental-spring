package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.ModelService;
import com.turkcell.rentacar.business.dtos.requests.CreateModelRequest;
import com.turkcell.rentacar.business.dtos.requests.UpdateModelRequest;
import com.turkcell.rentacar.business.dtos.responses.CreatedModelResponse;
import com.turkcell.rentacar.business.dtos.responses.GetAllModelsListItemDto;
import com.turkcell.rentacar.business.dtos.responses.GetModelResponse;
import com.turkcell.rentacar.business.dtos.responses.UpdatedModelResponse;
import com.turkcell.rentacar.business.rules.ModelBusinessRules;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.ModelRepository;
import com.turkcell.rentacar.entities.concretes.Model;
import lombok.AllArgsConstructor;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ModelManager implements ModelService {
    private final ModelRepository modelRepository; // IoC
    private final ModelBusinessRules modelBusinessRules;
    private final ModelMapperService modelMapperService;

    @Override
    public CreatedModelResponse add(CreateModelRequest createModelRequest) {
        modelBusinessRules.brandIdShouldBeExist(createModelRequest.getBrandId());
        modelBusinessRules.fuelIdShouldBeExist(createModelRequest.getFuelId());
        modelBusinessRules.transmissionIdShouldBeExist(createModelRequest.getTransmissionId());

        Model model = modelMapperService.forRequest().map(createModelRequest, Model.class);
        model.setCreatedDate(LocalDateTime.now());

        Model createdModel = modelRepository.save(model);
        return modelMapperService.forResponse().map(createdModel, CreatedModelResponse.class);
    }

    @Override
    public UpdatedModelResponse update(int id, UpdateModelRequest updateModelRequest) {
        modelBusinessRules.modelIdShouldBeExist(id);
        modelBusinessRules.brandIdShouldBeExist(updateModelRequest.getBrandId());
        modelBusinessRules.fuelIdShouldBeExist(updateModelRequest.getFuelId());
        modelBusinessRules.transmissionIdShouldBeExist(updateModelRequest.getTransmissionId());

        Model model = modelMapperService.forRequest().map(updateModelRequest, Model.class);
        model.setId(id);
        Model updatedModel = modelRepository.save(model);
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
        Type listType = new TypeToken<List<GetAllModelsListItemDto>>() {
        }.getType();
        return modelMapperService.forResponse().map(models, listType);
    }

    @Override
    public GetModelResponse get(int id) {
        Optional<Model> foundOptionalModel = modelRepository.findById(id);
        modelBusinessRules.modelShouldBeExist(foundOptionalModel);
        return modelMapperService.forResponse().map(foundOptionalModel.get(), GetModelResponse.class);
    }
}
