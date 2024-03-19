package com.turkcell.rentacar.core.utilities.mapping;

import org.modelmapper.ModelMapper;

public interface ModelMapperService {
    ModelMapper forResponse();

    ModelMapper forRequest();
}