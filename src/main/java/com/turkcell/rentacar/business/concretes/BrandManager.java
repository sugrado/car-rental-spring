package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.BrandService;
import com.turkcell.rentacar.business.dtos.requests.CreateBrandRequest;
import com.turkcell.rentacar.business.dtos.requests.UpdateBrandRequest;
import com.turkcell.rentacar.business.dtos.responses.CreatedBrandResponse;
import com.turkcell.rentacar.business.dtos.responses.GetAllBrandsListItemDto;
import com.turkcell.rentacar.business.dtos.responses.GetBrandResponse;
import com.turkcell.rentacar.business.dtos.responses.UpdatedBrandResponse;
import com.turkcell.rentacar.business.dtos.responses.common.GetListResponse;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.BrandRepository;
import com.turkcell.rentacar.entities.concretes.Brand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BrandManager implements BrandService {
    BrandRepository brandRepository; // IoC
    private static final String brandNotFoundMessage = "Brand not found";
    private static final String brandAlreadyExistsMessage = "Brand already exists";
    ModelMapperService modelMapperService;

    @Override
    public CreatedBrandResponse add(CreateBrandRequest createBrandRequest) {
        brandNameCanNotBeDuplicatedWhenInserted(createBrandRequest.getName());

        Brand brand = modelMapperService.forRequest().map(createBrandRequest, Brand.class);
        brand.setCreatedDate(LocalDateTime.now());

        Brand createdBrand = brandRepository.save(brand);
        return modelMapperService.forResponse().map(createdBrand, CreatedBrandResponse.class);
    }

    @Override
    public UpdatedBrandResponse update(int id, UpdateBrandRequest updateBrandRequest) {
        Optional<Brand> foundOptionalBrand = brandRepository.findById(id);
        brandShouldBeExist(foundOptionalBrand);
        brandNameCanNotBeDuplicatedWhenUpdated(id, updateBrandRequest.getName());

        Brand brandToUpdate = foundOptionalBrand.get();
        modelMapperService.forRequest().map(updateBrandRequest, brandToUpdate);
        brandToUpdate.setUpdatedDate(LocalDateTime.now());

        Brand updatedBrand = brandRepository.save(brandToUpdate);
        return modelMapperService.forResponse().map(updatedBrand, UpdatedBrandResponse.class);
    }

    @Override
    public void delete(int id) {
        Optional<Brand> foundOptionalBrand = brandRepository.findById(id);
        brandShouldBeExist(foundOptionalBrand);
        brandRepository.delete(foundOptionalBrand.get());
    }

    @Override
    public GetListResponse<GetAllBrandsListItemDto> getAll() {
        List<Brand> brands = brandRepository.findAll();
        return modelMapperService.forResponse().map(brands, GetListResponse.class);
    }

    @Override
    public GetBrandResponse get(int id) {
        Optional<Brand> foundOptionalBrand = brandRepository.findById(id);
        brandShouldBeExist(foundOptionalBrand);
        return modelMapperService.forResponse().map(foundOptionalBrand.get(), GetBrandResponse.class);
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

    private void brandNameCanNotBeDuplicatedWhenUpdated(int id, String name) {
        boolean exists = brandRepository.existsByNameIgnoreCaseAndIdIsNot(name.trim(), id);
        if (exists) {
            throw new RuntimeException(brandAlreadyExistsMessage);
        }
    }
}
