package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.FuelService;
import com.turkcell.rentacar.business.dtos.requests.fuels.CreateFuelRequest;
import com.turkcell.rentacar.business.dtos.requests.fuels.UpdateFuelRequest;
import com.turkcell.rentacar.business.dtos.responses.fuels.CreatedFuelResponse;
import com.turkcell.rentacar.business.dtos.responses.fuels.GetAllFuelsListItemDto;
import com.turkcell.rentacar.business.dtos.responses.fuels.GetFuelResponse;
import com.turkcell.rentacar.business.dtos.responses.fuels.UpdatedFuelResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/fuels")
public class FuelsController {
    private FuelService fuelService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedFuelResponse add(@Valid @RequestBody CreateFuelRequest fuel) {
        return fuelService.add(fuel);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedFuelResponse update(@PathVariable int id, @Valid @RequestBody UpdateFuelRequest fuel) {
        return fuelService.update(id, fuel);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        fuelService.delete(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetFuelResponse get(@PathVariable int id) {
        return fuelService.get(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllFuelsListItemDto> getAll() {
        return fuelService.getAll();
    }
}

