package com.turkcell.rentacar.entities.concretes;

import com.turkcell.rentacar.core.entities.BaseEntity;
import jakarta.persistence.*;
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
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "fuel_id")
    private Fuel fuel;

    @ManyToOne
    @JoinColumn(name = "transmission_id")
    private Transmission transmission;
}
