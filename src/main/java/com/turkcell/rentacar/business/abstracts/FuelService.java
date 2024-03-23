package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.fuels.CreateFuelRequest;
import com.turkcell.rentacar.business.dtos.requests.fuels.UpdateFuelRequest;
import com.turkcell.rentacar.business.dtos.responses.fuels.CreatedFuelResponse;
import com.turkcell.rentacar.business.dtos.responses.fuels.GetAllFuelsListItemDto;
import com.turkcell.rentacar.business.dtos.responses.fuels.GetFuelResponse;
import com.turkcell.rentacar.business.dtos.responses.fuels.UpdatedFuelResponse;

import java.util.List;

public interface FuelService {
    CreatedFuelResponse add(CreateFuelRequest createFuelRequest);

    UpdatedFuelResponse update(int id, UpdateFuelRequest updateFuelRequest);

    void delete(int id);

    List<GetAllFuelsListItemDto> getAll();

    GetFuelResponse get(int id);
}
