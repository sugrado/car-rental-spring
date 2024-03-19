package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.CreateTransmissionRequest;
import com.turkcell.rentacar.business.dtos.requests.UpdateTransmissionRequest;
import com.turkcell.rentacar.business.dtos.responses.CreatedTransmissionResponse;
import com.turkcell.rentacar.business.dtos.responses.GetAllTransmissionsListItemDto;
import com.turkcell.rentacar.business.dtos.responses.GetTransmissionResponse;
import com.turkcell.rentacar.business.dtos.responses.UpdatedTransmissionResponse;
import com.turkcell.rentacar.business.dtos.responses.common.GetListResponse;

public interface TransmissionService {
    CreatedTransmissionResponse add(CreateTransmissionRequest createTransmissionRequest);

    UpdatedTransmissionResponse update(int id, UpdateTransmissionRequest updateTransmissionRequest);

    void delete(int id);

    GetListResponse<GetAllTransmissionsListItemDto> getAll();

    GetTransmissionResponse get(int id);
}
