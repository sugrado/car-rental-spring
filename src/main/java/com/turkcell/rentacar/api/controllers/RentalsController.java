package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.RentalService;
import com.turkcell.rentacar.business.dtos.requests.rentals.CreateRentalRequest;
import com.turkcell.rentacar.business.dtos.requests.rentals.ReturnCarRequest;
import com.turkcell.rentacar.business.dtos.requests.rentals.UpdateRentalRequest;
import com.turkcell.rentacar.business.dtos.responses.rentals.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/rentals")
public class RentalsController {
    private RentalService rentalService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedRentalResponse add(@Valid @RequestBody CreateRentalRequest createRentalRequest) {
        return rentalService.add(createRentalRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedRentalResponse update(@PathVariable int id, @Valid @RequestBody UpdateRentalRequest rental) {
        return rentalService.update(id, rental);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public ReturnedCarResponse returnCar(@Valid @RequestBody ReturnCarRequest returnCarRequest) {
        return rentalService.returnCar(returnCarRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        rentalService.delete(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetRentalResponse get(@PathVariable int id) {
        return rentalService.get(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllRentalsListItemDto> getAll() {
        return rentalService.getAll();
    }
}

