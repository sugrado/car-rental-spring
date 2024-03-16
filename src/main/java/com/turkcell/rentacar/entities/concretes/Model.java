package com.turkcell.rentacar.entities.concretes;

import com.turkcell.rentacar.core.entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "models")
public class Model extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "daily_price")
    private Double dailyPrice;

    @ManyToOne
    private Brand brand;

    @ManyToOne
    private Fuel fuel;

    @ManyToOne
    private Transmission transmission;
}
