package com.turkcell.rentacar.dataAccess.abstracts;

import com.turkcell.rentacar.entities.concretes.Brand;
import com.turkcell.rentacar.entities.concretes.Fuel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuelRepository extends JpaRepository<Fuel, Integer> {
    Optional<Fuel> getByNameContainingIgnoreCase(String name);
    boolean existsByNameAndIdIsNot(String name, int id);
}
