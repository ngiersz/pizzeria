package com.put.bd.pizzeria.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "street", columnDefinition = "NVARCHAR(100)")
    @NotNull
    String street;

    @Column(name = "apartment_number")
    @NotNull
    Integer apartmentNumber;

    @Column(name = "city", columnDefinition = "NVARCHAR(100)")
    @NotNull
    String city;

    @Column(name = "district", columnDefinition = "NVARCHAR(100)")
    @NotNull
    String district;

    public Address(Integer id, Address address) {
        this.id = id;
        this.street = address.street;
        this.apartmentNumber = address.apartmentNumber;
        this.city = address.city;
        this.district = address.district;
    }
}
