package com.put.bd.pizzeria.domain;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
@JsonApiResource(type = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonApiId
    Integer id;

    @Column(name = "street", columnDefinition = "NVARCHAR(100)")
    String street;

    @Column(name = "apartment_number")
    Integer apartmentNumber;

    @Column(name = "city", columnDefinition = "NVARCHAR(100)")
    String city;

    @Column(name = "district", columnDefinition = "NVARCHAR(100)")
    String district;

}
