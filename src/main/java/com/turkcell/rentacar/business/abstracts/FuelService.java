package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.entities.concretes.Fuel;

import java.util.List;

public interface FuelService {
    Fuel add(Fuel fuel);
    Fuel update(Fuel fuel);
    void delete(int id);
    List<Fuel> getAll();
    Fuel get(int id);
}
