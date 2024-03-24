package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.MaintenanceService;
import com.turkcell.rentacar.business.dtos.requests.maintenances.CreateMaintenanceRequest;
import com.turkcell.rentacar.business.dtos.requests.maintenances.UpdateMaintenanceRequest;
import com.turkcell.rentacar.business.dtos.responses.maintenances.CreatedMaintenanceResponse;
import com.turkcell.rentacar.business.dtos.responses.maintenances.GetAllMaintenancesListItemDto;
import com.turkcell.rentacar.business.dtos.responses.maintenances.GetMaintenanceResponse;
import com.turkcell.rentacar.business.dtos.responses.maintenances.UpdatedMaintenanceResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/maintenances")
public class MaintenancesController {
    private MaintenanceService maintenanceService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedMaintenanceResponse add(@Valid @RequestBody CreateMaintenanceRequest createMaintenanceRequest) {
        return maintenanceService.add(createMaintenanceRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedMaintenanceResponse update(@PathVariable int id, @Valid @RequestBody UpdateMaintenanceRequest maintenance) {
        return maintenanceService.update(id, maintenance);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        maintenanceService.delete(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetMaintenanceResponse get(@PathVariable int id) {
        return maintenanceService.get(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllMaintenancesListItemDto> getAll() {
        return maintenanceService.getAll();
    }
}

