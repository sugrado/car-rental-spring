package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.cars.CreateCarRequest;
import com.turkcell.rentacar.business.dtos.requests.cars.UpdateCarRequest;
import com.turkcell.rentacar.business.dtos.responses.cars.CreatedCarResponse;
import com.turkcell.rentacar.business.dtos.responses.cars.GetAllCarsListItemDto;
import com.turkcell.rentacar.business.dtos.responses.cars.GetCarResponse;
import com.turkcell.rentacar.business.dtos.responses.cars.UpdatedCarResponse;

import java.util.List;

public interface CarService {
    CreatedCarResponse add(CreateCarRequest createCarRequest);

    UpdatedCarResponse update(int id, UpdateCarRequest updateCarRequest);

    void delete(int id);

    List<GetAllCarsListItemDto> getAll();

    GetCarResponse get(int id);
}
