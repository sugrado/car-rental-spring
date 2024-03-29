package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.cars.CreateCarRequest;
import com.turkcell.rentacar.business.dtos.requests.cars.UpdateCarRequest;
import com.turkcell.rentacar.business.dtos.responses.cars.CreatedCarResponse;
import com.turkcell.rentacar.business.dtos.responses.cars.GetAllCarsListItemDto;
import com.turkcell.rentacar.business.dtos.responses.cars.GetCarResponse;
import com.turkcell.rentacar.business.dtos.responses.cars.UpdatedCarResponse;
import com.turkcell.rentacar.entities.enums.CarState;

import java.util.List;

public interface CarService {
    CreatedCarResponse add(CreateCarRequest createCarRequest);

    void updateState(int carId, CarState carState);

    UpdatedCarResponse update(int id, UpdateCarRequest updateCarRequest);

    void delete(int id);

    List<GetAllCarsListItemDto> getAll();

    GetCarResponse get(int id);

    double calculatePriceByDays(int carId, short days);

    double calculateLateFeeByDays(int carId, short days);
}
