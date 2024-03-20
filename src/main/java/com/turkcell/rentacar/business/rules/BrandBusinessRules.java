package com.turkcell.rentacar.business.rules;

import com.turkcell.rentacar.core.utilities.exceptions.types.BusinessException;
import com.turkcell.rentacar.dataAccess.abstracts.BrandRepository;
import com.turkcell.rentacar.entities.concretes.Brand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BrandBusinessRules {
    private final BrandRepository brandRepository;
    private static final String brandNotFoundMessage = "Brand not found";
    private static final String brandAlreadyExistsMessage = "Brand already exists";

    public void brandShouldBeExist(Optional<Brand> brand) {
        if (brand.isEmpty()) {
            throw new BusinessException(brandNotFoundMessage);
        }
    }

    public void brandIdShouldBeExist(int brandId) {
        Optional<Brand> brand = brandRepository.findById(brandId);
        if (brand.isEmpty()) {
            throw new BusinessException(brandNotFoundMessage);
        }
    }

    public void brandNameCanNotBeDuplicatedWhenInserted(String name) {
        Optional<Brand> foundOptionalBrand = brandRepository.findByNameIgnoreCase(name.trim());
        if (foundOptionalBrand.isPresent()) {
            throw new BusinessException(brandAlreadyExistsMessage);
        }
    }

    public void brandNameCanNotBeDuplicatedWhenUpdated(int id, String name) {
        boolean exists = brandRepository.existsByNameIgnoreCaseAndIdIsNot(name.trim(), id);
        if (exists) {
            throw new BusinessException(brandAlreadyExistsMessage);
        }
    }
}
