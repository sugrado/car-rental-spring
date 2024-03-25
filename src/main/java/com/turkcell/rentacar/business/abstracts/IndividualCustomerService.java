package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.individualCustomers.CreateIndividualCustomerRequest;
import com.turkcell.rentacar.business.dtos.requests.individualCustomers.UpdateIndividualCustomerRequest;
import com.turkcell.rentacar.business.dtos.responses.individualCustomers.CreatedIndividualCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.individualCustomers.GetAllIndividualCustomersListItemDto;
import com.turkcell.rentacar.business.dtos.responses.individualCustomers.GetIndividualCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.individualCustomers.UpdatedIndividualCustomerResponse;

import java.util.List;

public interface IndividualCustomerService {
    CreatedIndividualCustomerResponse add(CreateIndividualCustomerRequest createIndividualCustomerRequest);

    UpdatedIndividualCustomerResponse update(int id, UpdateIndividualCustomerRequest updateIndividualCustomerRequest);

    void delete(int id);

    List<GetAllIndividualCustomersListItemDto> getAll();

    GetIndividualCustomerResponse get(int id);

    void updateFindeksScore(int id);
}
