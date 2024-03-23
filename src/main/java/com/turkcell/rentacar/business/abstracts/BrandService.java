package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.brands.CreateBrandRequest;
import com.turkcell.rentacar.business.dtos.requests.brands.UpdateBrandRequest;
import com.turkcell.rentacar.business.dtos.responses.brands.CreatedBrandResponse;
import com.turkcell.rentacar.business.dtos.responses.brands.GetAllBrandsListItemDto;
import com.turkcell.rentacar.business.dtos.responses.brands.GetBrandResponse;
import com.turkcell.rentacar.business.dtos.responses.brands.UpdatedBrandResponse;

import java.util.List;

public interface BrandService {
    CreatedBrandResponse add(CreateBrandRequest createBrandRequest);

    UpdatedBrandResponse update(int id, UpdateBrandRequest updateBrandRequest);

    void delete(int id);

    List<GetAllBrandsListItemDto> getAll();

    GetBrandResponse get(int id);
}
