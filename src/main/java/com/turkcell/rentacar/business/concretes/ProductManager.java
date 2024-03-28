package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.ProductService;
import com.turkcell.rentacar.business.dtos.requests.products.CreateProductRequest;
import com.turkcell.rentacar.business.dtos.requests.products.UpdateProductRequest;
import com.turkcell.rentacar.business.dtos.responses.products.CreatedProductResponse;
import com.turkcell.rentacar.business.dtos.responses.products.GetAllProductsListItemDto;
import com.turkcell.rentacar.business.dtos.responses.products.GetProductResponse;
import com.turkcell.rentacar.business.dtos.responses.products.UpdatedProductResponse;
import com.turkcell.rentacar.business.rules.ProductBusinessRules;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.ProductRepository;
import com.turkcell.rentacar.entities.concretes.Product;
import com.turkcell.rentacar.entities.concretes.RentalProduct;
import lombok.AllArgsConstructor;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductManager implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapperService modelMapperService;
    private final ProductBusinessRules productBusinessRules;

    @Override
    public CreatedProductResponse add(CreateProductRequest createProductRequest) {
        productBusinessRules.productNameCanNotBeDuplicatedWhenInserted(createProductRequest.getName());

        Product product = modelMapperService.forRequest().map(createProductRequest, Product.class);

        Product createdProduct = productRepository.save(product);
        return modelMapperService.forResponse().map(createdProduct, CreatedProductResponse.class);
    }

    @Override
    public UpdatedProductResponse update(int id, UpdateProductRequest updateProductRequest) {
        productBusinessRules.productIdShouldBeExist(id);
        productBusinessRules.productNameCanNotBeDuplicatedWhenUpdated(id, updateProductRequest.getName());
        Product productToUpdate = modelMapperService.forRequest().map(updateProductRequest, Product.class);
        productToUpdate.setId(id);
        Product updatedProduct = productRepository.save(productToUpdate);
        return modelMapperService.forResponse().map(updatedProduct, UpdatedProductResponse.class);
    }

    @Override
    public void delete(int id) {
        Optional<Product> foundOptionalProduct = productRepository.findById(id);
        productBusinessRules.productShouldBeExist(foundOptionalProduct);
        productRepository.delete(foundOptionalProduct.get());
    }

    @Override
    public List<GetAllProductsListItemDto> getAll() {
        List<Product> products = productRepository.findAll();
        return modelMapperService.forResponse().map(products, new TypeToken<List<GetAllProductsListItemDto>>() {
        }.getType());
    }

    @Override
    public GetProductResponse get(int id) {
        Optional<Product> foundOptionalProduct = productRepository.findById(id);
        productBusinessRules.productShouldBeExist(foundOptionalProduct);
        return modelMapperService.forResponse().map(foundOptionalProduct.get(), GetProductResponse.class);
    }

    @Override
    public double calculateTotalPrice(List<RentalProduct> rentalProducts) {
        return productRepository.calculateTotalPrice(rentalProducts);
    }
}
