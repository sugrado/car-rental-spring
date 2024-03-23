package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.corporateCustomers.CreateCorporateCustomerRequest;
import com.turkcell.rentacar.business.dtos.requests.corporateCustomers.UpdateCorporateCustomerRequest;
import com.turkcell.rentacar.business.dtos.responses.corporateCustomers.CreatedCorporateCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.corporateCustomers.GetAllCorporateCustomersListItemDto;
import com.turkcell.rentacar.business.dtos.responses.corporateCustomers.GetCorporateCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.corporateCustomers.UpdatedCorporateCustomerResponse;

import java.util.List;

public interface CorporateCustomerService {
    CreatedCorporateCustomerResponse add(CreateCorporateCustomerRequest createCorporateCustomerRequest);

    UpdatedCorporateCustomerResponse update(int id, UpdateCorporateCustomerRequest updateCorporateCustomerRequest);

    void delete(int id);

    List<GetAllCorporateCustomersListItemDto> getAll();

    GetCorporateCustomerResponse get(int id);
}
