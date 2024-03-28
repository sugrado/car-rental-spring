package com.turkcell.rentacar.business.rules;

import com.turkcell.rentacar.business.constants.messages.CustomerMessages;
import com.turkcell.rentacar.core.utilities.exceptions.types.BusinessException;
import com.turkcell.rentacar.dataAccess.abstracts.CustomerRepository;
import com.turkcell.rentacar.entities.concretes.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerBusinessRules {
    private final CustomerRepository customerRepository;

    public void customerShouldBeExist(Optional<Customer> customer) {
        if (customer.isEmpty()) {
            throw new BusinessException(CustomerMessages.CUSTOMER_NOT_FOUND);
        }
    }
}
