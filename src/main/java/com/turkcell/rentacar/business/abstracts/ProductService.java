package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.products.CreateProductRequest;
import com.turkcell.rentacar.business.dtos.requests.products.UpdateProductRequest;
import com.turkcell.rentacar.business.dtos.responses.products.CreatedProductResponse;
import com.turkcell.rentacar.business.dtos.responses.products.GetAllProductsListItemDto;
import com.turkcell.rentacar.business.dtos.responses.products.GetProductResponse;
import com.turkcell.rentacar.business.dtos.responses.products.UpdatedProductResponse;

import java.util.List;

public interface ProductService {
    CreatedProductResponse add(CreateProductRequest createProductRequest);

    UpdatedProductResponse update(int id, UpdateProductRequest updateProductRequest);

    void delete(int id);

    List<GetAllProductsListItemDto> getAll();

    GetProductResponse get(int id);
}
