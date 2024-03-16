package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.BrandService;
import com.turkcell.rentacar.dataAccess.abstracts.BrandRepository;
import com.turkcell.rentacar.entities.concretes.Brand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BrandManager implements BrandService {
    BrandRepository brandRepository; // IoC
    private static final String brandNotFoundMessage = "Brand not found";
    private static final String brandAlreadyExistsMessage = "Brand already exists";

    @Override
    public Brand add(Brand brand) {
        // TODO: Validation rules
        brandNameCanNotBeDuplicatedWhenInserted(brand.getName());
        return brandRepository.save(brand);
    }

    @Override
    public Brand update(Brand brand) {
        // TODO: Validation rules
        Optional<Brand> foundOptionalBrand = brandRepository.findById(brand.getId());
        brandShouldBeExist(foundOptionalBrand);
        brandNameCanNotBeDuplicatedWhenUpdated(brand);

        Brand brandToUpdate = foundOptionalBrand.get();
        brandToUpdate.setName(brand.getName()); // TODO: mapper

        return brandRepository.save(brandToUpdate);
    }

    @Override
    public void delete(int id) {
        Optional<Brand> foundOptionalBrand = brandRepository.findById(id);
        brandShouldBeExist(foundOptionalBrand);
        brandRepository.delete(foundOptionalBrand.get());
    }

    @Override
    public List<Brand> getAll() {
        return brandRepository.findAll();
    }

    @Override
    public Brand get(int id) {
        Optional<Brand> foundOptionalBrand = brandRepository.findById(id);
        brandShouldBeExist(foundOptionalBrand);
        return foundOptionalBrand.get();
    }

    // temp business rules
    // TODO: BrandBusinessRules
    private void brandShouldBeExist(Optional<Brand> brand) {
        if (brand.isEmpty()) {
            throw new RuntimeException(brandNotFoundMessage);
        }
    }

    private void brandNameCanNotBeDuplicatedWhenInserted(String name) {
        Optional<Brand> foundOptionalBrand = brandRepository.getByNameIgnoreCase(name.trim());
        if (foundOptionalBrand.isPresent()) {
            throw new RuntimeException(brandAlreadyExistsMessage);
        }
    }

    private void brandNameCanNotBeDuplicatedWhenUpdated(Brand brand) {
        boolean exists = brandRepository.existsByNameIgnoreCaseAndIdIsNot(brand.getName().trim(), brand.getId());
        if (exists) {
            throw new RuntimeException(brandAlreadyExistsMessage);
        }
    }
}
