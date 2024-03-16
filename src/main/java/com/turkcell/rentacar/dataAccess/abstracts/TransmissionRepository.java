package com.turkcell.rentacar.dataAccess.abstracts;

import com.turkcell.rentacar.entities.concretes.Transmission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransmissionRepository extends JpaRepository<Transmission, Integer> {
    Optional<Transmission> getByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCaseAndIdIsNot(String name, int id);
}
