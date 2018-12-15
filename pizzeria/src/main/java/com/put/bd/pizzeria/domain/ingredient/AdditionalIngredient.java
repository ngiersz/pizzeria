package com.put.bd.pizzeria.domain.ingredient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "additional_ingredient")
@Getter
@Setter
@AllArgsConstructor
public class AdditionalIngredient {

    @Id
    Integer id;

    @Column(name = "ordered_dish_id", columnDefinition = "INT")
    Integer orderedDishId;

    @Column(name = "ingredient_id", columnDefinition = "INT")
    Integer ingredientId;

}
