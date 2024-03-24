package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.customers.CreateCustomerRequest;
import com.turkcell.rentacar.business.dtos.responses.customers.CreatedCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.customers.GetAllCustomersListItemDto;
import com.turkcell.rentacar.business.dtos.responses.customers.GetCustomerResponse;

import java.util.List;

public interface CustomerService {
    CreatedCustomerResponse add(CreateCustomerRequest createCustomerRequest);

    List<GetAllCustomersListItemDto> getAll();

    GetCustomerResponse get(int id);
}
