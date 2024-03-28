package com.turkcell.rentacar.dataAccess.abstracts;

import com.turkcell.rentacar.entities.concretes.Product;
import com.turkcell.rentacar.entities.concretes.RentalProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCaseAndIdIsNot(String name, int id);

    int countByIdIn(List<Integer> ids);

    @Query("SELECT SUM(p.dailyPrice * r.quantity) FROM Product p JOIN RentalProduct r ON p.id = r.product.id WHERE r IN :items")
    double calculateTotalPrice(List<RentalProduct> items);
}
