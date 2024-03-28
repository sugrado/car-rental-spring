package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.ProductService;
import com.turkcell.rentacar.business.dtos.requests.products.CreateProductRequest;
import com.turkcell.rentacar.business.dtos.requests.products.UpdateProductRequest;
import com.turkcell.rentacar.business.dtos.responses.products.CreatedProductResponse;
import com.turkcell.rentacar.business.dtos.responses.products.GetAllProductsListItemDto;
import com.turkcell.rentacar.business.dtos.responses.products.GetProductResponse;
import com.turkcell.rentacar.business.dtos.responses.products.UpdatedProductResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/products")
public class ProductsController {
    private ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedProductResponse add(@Valid @RequestBody CreateProductRequest createProductRequest) {
        return productService.add(createProductRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedProductResponse update(@PathVariable int id, @Valid @RequestBody UpdateProductRequest product) {
        return productService.update(id, product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        productService.delete(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetProductResponse get(@PathVariable int id) {
        return productService.get(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllProductsListItemDto> getAll() {
        return productService.getAll();
    }
}

