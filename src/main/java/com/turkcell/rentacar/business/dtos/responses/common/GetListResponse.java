package com.turkcell.rentacar.business.dtos.responses.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetListResponse<T> {
    private List<T> items;
}
