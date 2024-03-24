package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.CorporateCustomerService;
import com.turkcell.rentacar.business.dtos.requests.corporateCustomers.CreateCorporateCustomerRequest;
import com.turkcell.rentacar.business.dtos.requests.corporateCustomers.UpdateCorporateCustomerRequest;
import com.turkcell.rentacar.business.dtos.responses.corporateCustomers.CreatedCorporateCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.corporateCustomers.GetAllCorporateCustomersListItemDto;
import com.turkcell.rentacar.business.dtos.responses.corporateCustomers.GetCorporateCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.corporateCustomers.UpdatedCorporateCustomerResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/corporate-customers")
public class CorporateCustomersController {
    private CorporateCustomerService corporateCustomerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedCorporateCustomerResponse add(@Valid @RequestBody CreateCorporateCustomerRequest createCorporateCustomerRequest) {
        return corporateCustomerService.add(createCorporateCustomerRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedCorporateCustomerResponse update(@Valid @PathVariable int id, @RequestBody UpdateCorporateCustomerRequest corporateCustomer) {
        return corporateCustomerService.update(id, corporateCustomer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        corporateCustomerService.delete(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetCorporateCustomerResponse get(@PathVariable int id) {
        return corporateCustomerService.get(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllCorporateCustomersListItemDto> getAll() {
        return corporateCustomerService.getAll();
    }
}

