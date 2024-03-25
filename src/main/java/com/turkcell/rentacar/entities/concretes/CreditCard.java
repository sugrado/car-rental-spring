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
@Table(name = "credit_cards")
public class CreditCard extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "card_holder")
    private String cardHolder;

    @Column(name = "expiration_year")
    private short expirationYear;

    @Column(name = "expiration_month")
    private byte expirationMonth;

    @Column(name = "cvv")
    private short cvv;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
