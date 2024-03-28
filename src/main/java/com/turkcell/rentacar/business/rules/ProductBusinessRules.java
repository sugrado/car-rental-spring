package com.turkcell.rentacar.business.rules;

import com.turkcell.rentacar.business.constants.messages.ProductMessages;
import com.turkcell.rentacar.business.dtos.requests.rentalProducts.CreateRentalProductListItemDto;
import com.turkcell.rentacar.core.utilities.exceptions.types.BusinessException;
import com.turkcell.rentacar.dataAccess.abstracts.ProductRepository;
import com.turkcell.rentacar.entities.concretes.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductBusinessRules {
    private final ProductRepository productRepository;

    public void productShouldBeExist(Optional<Product> product) {
        if (product.isEmpty()) {
            throw new BusinessException(ProductMessages.PRODUCT_NOT_FOUND);
        }
    }

    public void productIdShouldBeExist(int productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()) {
            throw new BusinessException(ProductMessages.PRODUCT_NOT_FOUND);
        }
    }

    public void allProductsShouldBeExists(List<CreateRentalProductListItemDto> items) {
        int foundEntityCount = productRepository.countByIdIn(items.stream().map(CreateRentalProductListItemDto::getProductId).toList());
        if (foundEntityCount != items.size()) {
            throw new BusinessException(ProductMessages.SOME_PRODUCTS_NOT_FOUND);
        }
    }

    public void productNameCanNotBeDuplicatedWhenInserted(String name) {
        Optional<Product> foundOptionalProduct = productRepository.findByNameIgnoreCase(name.trim());
        if (foundOptionalProduct.isPresent()) {
            throw new BusinessException(ProductMessages.PRODUCT_ALREADY_EXISTS);
        }
    }

    public void productNameCanNotBeDuplicatedWhenUpdated(int id, String name) {
        boolean exists = productRepository.existsByNameIgnoreCaseAndIdIsNot(name.trim(), id);
        if (exists) {
            throw new BusinessException(ProductMessages.PRODUCT_ALREADY_EXISTS);
        }
    }
}
