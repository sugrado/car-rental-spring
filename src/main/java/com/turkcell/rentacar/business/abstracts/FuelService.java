package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.CreateFuelRequest;
import com.turkcell.rentacar.business.dtos.requests.UpdateFuelRequest;
import com.turkcell.rentacar.business.dtos.responses.CreatedFuelResponse;
import com.turkcell.rentacar.business.dtos.responses.GetFuelResponse;
import com.turkcell.rentacar.business.dtos.responses.UpdatedFuelResponse;
import com.turkcell.rentacar.entities.concretes.Fuel;

import java.util.List;

public interface FuelService {
    CreatedFuelResponse add(CreateFuelRequest createFuelRequest);

    UpdatedFuelResponse update(int id, UpdateFuelRequest updateFuelRequest);

    void delete(int id);

    List<Fuel> getAll();

    GetFuelResponse get(int id);
}
