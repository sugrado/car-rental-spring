package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.rentalProducts.CreateRentalProductRequest;
import com.turkcell.rentacar.business.dtos.responses.rentalProducts.CreatedRentalProductListItemDto;
import com.turkcell.rentacar.business.dtos.responses.rentalProducts.GetAllRentalProductsListItemDto;
import com.turkcell.rentacar.entities.concretes.RentalProduct;

import java.util.List;

public interface RentalProductService {
    List<CreatedRentalProductListItemDto> add(int rentalId, CreateRentalProductRequest createRentalProductRequest);

    List<RentalProduct> addRange(List<RentalProduct> rentalProducts);

    List<GetAllRentalProductsListItemDto> getAllByRental(int rentalId);
}
