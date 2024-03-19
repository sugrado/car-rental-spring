package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.CreateModelRequest;
import com.turkcell.rentacar.business.dtos.requests.UpdateModelRequest;
import com.turkcell.rentacar.business.dtos.responses.CreatedModelResponse;
import com.turkcell.rentacar.business.dtos.responses.GetAllModelsListItemDto;
import com.turkcell.rentacar.business.dtos.responses.GetModelResponse;
import com.turkcell.rentacar.business.dtos.responses.UpdatedModelResponse;
import com.turkcell.rentacar.business.dtos.responses.common.GetListResponse;

public interface ModelService {
    CreatedModelResponse add(CreateModelRequest createModelRequest);

    UpdatedModelResponse update(int id, UpdateModelRequest updateModelRequest);

    void delete(int id);

    GetListResponse<GetAllModelsListItemDto> getAll();

    GetModelResponse get(int id);
}
