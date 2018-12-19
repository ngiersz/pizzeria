package com.put.bd.pizzeria.domain.ingredient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ingredient")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {

    @Id
    @Column(name = "id")
    Integer id;

    @Column(name = "name", columnDefinition = "NVARCHAR(100)")
    String name;

    @Column(name = "price", columnDefinition = "MONEY")
    BigDecimal price;

    @Column(name = "quantity_in_storeroom", columnDefinition = "INT")
    Integer quantity;

}
