package com.turkcell.rentacar.dataAccess.abstracts;

import com.turkcell.rentacar.entities.concretes.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CreditCardRepository extends JpaRepository<CreditCard, Integer> {
    Optional<CreditCard> findByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCaseAndIdIsNot(String name, int id);
}
