package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.rentals.CreateRentalRequest;
import com.turkcell.rentacar.business.dtos.requests.rentals.ReturnCarRequest;
import com.turkcell.rentacar.business.dtos.requests.rentals.UpdateRentalRequest;
import com.turkcell.rentacar.business.dtos.responses.rentals.*;

import java.util.List;

public interface RentalService {
    CreatedRentalResponse add(CreateRentalRequest createRentalRequest);

    UpdatedRentalResponse update(int id, UpdateRentalRequest updateRentalRequest);

    void delete(int id);

    List<GetAllRentalsListItemDto> getAll();

    GetRentalResponse get(int id);

    ReturnedCarResponse returnCar(ReturnCarRequest returnCarRequest);
}
