package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.CreateBrandRequest;
import com.turkcell.rentacar.business.dtos.requests.UpdateBrandRequest;
import com.turkcell.rentacar.business.dtos.responses.CreatedBrandResponse;
import com.turkcell.rentacar.business.dtos.responses.GetAllBrandsListItemDto;
import com.turkcell.rentacar.business.dtos.responses.GetBrandResponse;
import com.turkcell.rentacar.business.dtos.responses.UpdatedBrandResponse;
import com.turkcell.rentacar.business.dtos.responses.common.GetListResponse;
import com.turkcell.rentacar.entities.concretes.Brand;

import java.util.List;

public interface BrandService {
    CreatedBrandResponse add(CreateBrandRequest createBrandRequest);

    UpdatedBrandResponse update(int id, UpdateBrandRequest updateBrandRequest);

    void delete(int id);

    GetListResponse<GetAllBrandsListItemDto> getAll();

    GetBrandResponse get(int id);
}
