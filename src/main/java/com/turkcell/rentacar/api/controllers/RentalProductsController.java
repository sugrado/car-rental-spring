package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.RentalProductService;
import com.turkcell.rentacar.business.dtos.requests.rentalProducts.CreateRentalProductRequest;
import com.turkcell.rentacar.business.dtos.responses.rentalProducts.CreatedRentalProductListItemDto;
import com.turkcell.rentacar.business.dtos.responses.rentalProducts.GetAllRentalProductsListItemDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/rentals/{rentalId}/products")
public class RentalProductsController {
    private RentalProductService rentalProductService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CreatedRentalProductListItemDto> add(@PathVariable int rentalId, @Valid @RequestBody CreateRentalProductRequest createRentalProductRequest) {
        return rentalProductService.add(rentalId, createRentalProductRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllRentalProductsListItemDto> getAllByRental(@PathVariable int rentalId) {
        return rentalProductService.getAllByRental(rentalId);
    }
}

