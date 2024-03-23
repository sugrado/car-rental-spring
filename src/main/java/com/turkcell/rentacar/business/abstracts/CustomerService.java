package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.responses.customers.GetAllCustomersListItemDto;
import com.turkcell.rentacar.business.dtos.responses.customers.GetCustomerResponse;

import java.util.List;

public interface CustomerService {

    List<GetAllCustomersListItemDto> getAll();

    GetCustomerResponse get(int id);
}
