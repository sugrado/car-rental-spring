package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.maintenances.CreateMaintenanceRequest;
import com.turkcell.rentacar.business.dtos.requests.maintenances.ReturnCarRequest;
import com.turkcell.rentacar.business.dtos.requests.maintenances.UpdateMaintenanceRequest;
import com.turkcell.rentacar.business.dtos.responses.maintenances.*;

import java.util.List;

public interface MaintenanceService {
    CreatedMaintenanceResponse add(CreateMaintenanceRequest createMaintenanceRequest);

    UpdatedMaintenanceResponse update(int id, UpdateMaintenanceRequest updateMaintenanceRequest);

    void delete(int id);

    List<GetAllMaintenancesListItemDto> getAll();

    GetMaintenanceResponse get(int id);

    ReturnedCarResponse returnCar(ReturnCarRequest returnCarRequest);
}
