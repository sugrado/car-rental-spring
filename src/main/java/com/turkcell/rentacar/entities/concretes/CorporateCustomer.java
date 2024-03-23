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
@Table(name = "corporate_customers")
public class CorporateCustomer extends BaseEntity {
    @OneToOne
    @MapsId
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
