package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.CustomerService;
import com.turkcell.rentacar.business.dtos.requests.customers.CreateCustomerRequest;
import com.turkcell.rentacar.business.dtos.responses.customers.CreatedCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.customers.GetAllCustomersListItemDto;
import com.turkcell.rentacar.business.dtos.responses.customers.GetCustomerResponse;
import com.turkcell.rentacar.business.rules.CustomerBusinessRules;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.CustomerRepository;
import com.turkcell.rentacar.entities.concretes.Customer;
import lombok.AllArgsConstructor;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CustomerManager implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapperService modelMapperService;
    private final CustomerBusinessRules customerBusinessRules;


    @Override
    public CreatedCustomerResponse add(CreateCustomerRequest createCustomerRequest) {
        Customer customer = modelMapperService.forRequest().map(createCustomerRequest, Customer.class);
        customer.setCreatedDate(LocalDateTime.now());

        Customer createdCustomer = customerRepository.save(customer);
        return modelMapperService.forResponse().map(createdCustomer, CreatedCustomerResponse.class);
    }

    @Override
    public List<GetAllCustomersListItemDto> getAll() {
        List<Customer> customers = customerRepository.findAll();
        return modelMapperService.forResponse().map(customers, new TypeToken<List<GetAllCustomersListItemDto>>() {
        }.getType());
    }

    @Override
    public GetCustomerResponse get(int id) {
        Optional<Customer> foundOptionalCustomer = customerRepository.findById(id);
        customerBusinessRules.customerShouldBeExist(foundOptionalCustomer);
        return modelMapperService.forResponse().map(foundOptionalCustomer.get(), GetCustomerResponse.class);
    }
}
