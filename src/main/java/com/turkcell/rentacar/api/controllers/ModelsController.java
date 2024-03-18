package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.ModelService;
import com.turkcell.rentacar.business.dtos.requests.CreateModelRequest;
import com.turkcell.rentacar.business.dtos.requests.UpdateModelRequest;
import com.turkcell.rentacar.business.dtos.responses.CreatedModelResponse;
import com.turkcell.rentacar.business.dtos.responses.GetModelResponse;
import com.turkcell.rentacar.business.dtos.responses.UpdatedModelResponse;
import com.turkcell.rentacar.entities.concretes.Model;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/models")
public class ModelsController {
    private ModelService modelService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedModelResponse add(@RequestBody CreateModelRequest model) {
        return modelService.add(model);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedModelResponse update(@PathVariable int id, @RequestBody UpdateModelRequest model) {
        return modelService.update(id, model);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        modelService.delete(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetModelResponse get(@PathVariable int id) {
        return modelService.get(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Model> getAll() {
        return modelService.getAll();
    }
}

