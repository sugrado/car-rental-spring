package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.TransmissionService;
import com.turkcell.rentacar.business.dtos.requests.CreateTransmissionRequest;
import com.turkcell.rentacar.business.dtos.requests.UpdateTransmissionRequest;
import com.turkcell.rentacar.business.dtos.responses.CreatedTransmissionResponse;
import com.turkcell.rentacar.business.dtos.responses.GetTransmissionResponse;
import com.turkcell.rentacar.business.dtos.responses.UpdatedTransmissionResponse;
import com.turkcell.rentacar.entities.concretes.Transmission;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/transmissions")
public class TransmissionsController {
    private TransmissionService transmissionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedTransmissionResponse add(@RequestBody CreateTransmissionRequest transmission) {
        return transmissionService.add(transmission);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedTransmissionResponse update(@PathVariable int id, @RequestBody UpdateTransmissionRequest transmission) {
        return transmissionService.update(id, transmission);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        transmissionService.delete(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetTransmissionResponse get(@PathVariable int id) {
        return transmissionService.get(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Transmission> getAll() {
        return transmissionService.getAll();
    }
}

