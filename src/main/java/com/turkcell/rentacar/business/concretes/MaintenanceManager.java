package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.CarService;
import com.turkcell.rentacar.business.abstracts.MaintenanceService;
import com.turkcell.rentacar.business.dtos.requests.maintenances.CreateMaintenanceRequest;
import com.turkcell.rentacar.business.dtos.requests.maintenances.UpdateMaintenanceRequest;
import com.turkcell.rentacar.business.dtos.responses.maintenances.CreatedMaintenanceResponse;
import com.turkcell.rentacar.business.dtos.responses.maintenances.GetAllMaintenancesListItemDto;
import com.turkcell.rentacar.business.dtos.responses.maintenances.GetMaintenanceResponse;
import com.turkcell.rentacar.business.dtos.responses.maintenances.UpdatedMaintenanceResponse;
import com.turkcell.rentacar.business.rules.CarBusinessRules;
import com.turkcell.rentacar.business.rules.MaintenanceBusinessRules;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.MaintenanceRepository;
import com.turkcell.rentacar.entities.concretes.Maintenance;
import com.turkcell.rentacar.entities.enums.CarState;
import lombok.AllArgsConstructor;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class MaintenanceManager implements MaintenanceService {
    private final MaintenanceRepository maintenanceRepository;
    private final ModelMapperService modelMapperService;
    private final MaintenanceBusinessRules maintenanceBusinessRules;
    private final CarBusinessRules carBusinessRules;
    private final CarService carService;

    @Override
    public CreatedMaintenanceResponse add(CreateMaintenanceRequest createMaintenanceRequest) {
        carBusinessRules.carIdShouldBeExist(createMaintenanceRequest.getCarId());
        carBusinessRules.carShouldBeAvailable(createMaintenanceRequest.getCarId());
        Maintenance maintenance = modelMapperService.forRequest().map(createMaintenanceRequest, Maintenance.class);
        maintenance.setCreatedDate(LocalDateTime.now());

        carService.updateState(createMaintenanceRequest.getCarId(), CarState.MAINTENANCE);

        Maintenance createdMaintenance = maintenanceRepository.save(maintenance);
        return modelMapperService.forResponse().map(createdMaintenance, CreatedMaintenanceResponse.class);
    }

    @Override
    public UpdatedMaintenanceResponse update(int id, UpdateMaintenanceRequest updateMaintenanceRequest) {
        maintenanceBusinessRules.maintenanceIdShouldBeExist(id);
        Maintenance maintenanceToUpdate = modelMapperService.forRequest().map(updateMaintenanceRequest, Maintenance.class);
        maintenanceToUpdate.setId(id);
        Maintenance updatedMaintenance = maintenanceRepository.save(maintenanceToUpdate);
        return modelMapperService.forResponse().map(updatedMaintenance, UpdatedMaintenanceResponse.class);
    }

    @Override
    public void delete(int id) {
        Optional<Maintenance> foundOptionalMaintenance = maintenanceRepository.findById(id);
        maintenanceBusinessRules.maintenanceShouldBeExist(foundOptionalMaintenance);
        maintenanceRepository.delete(foundOptionalMaintenance.get());
    }

    @Override
    public List<GetAllMaintenancesListItemDto> getAll() {
        List<Maintenance> maintenances = maintenanceRepository.findAll();
        return modelMapperService.forResponse().map(maintenances, new TypeToken<List<GetAllMaintenancesListItemDto>>() {
        }.getType());
    }

    @Override
    public GetMaintenanceResponse get(int id) {
        Optional<Maintenance> foundOptionalMaintenance = maintenanceRepository.findById(id);
        maintenanceBusinessRules.maintenanceShouldBeExist(foundOptionalMaintenance);
        return modelMapperService.forResponse().map(foundOptionalMaintenance.get(), GetMaintenanceResponse.class);
    }
}
