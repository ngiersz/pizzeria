package com.put.bd.pizzeria.domain.ingredient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "ingredient")
@Getter
@Setter
@AllArgsConstructor
public class Ingredient {

    @Id
    Integer id;

    @Column(name = "name", columnDefinition = "NVARCHAR(100)")
    String name;

    @Column(name = "price", columnDefinition = "MONEY")
    BigDecimal price;

    @Column(name = "quantity_in_storeroom", columnDefinition = "INT")
    Integer quantity;


}
