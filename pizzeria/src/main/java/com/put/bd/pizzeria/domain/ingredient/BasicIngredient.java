package com.put.bd.pizzeria.domain.ingredient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "basic_ingredient")
@Getter
@Setter
@AllArgsConstructor
public class BasicIngredient {

    @Id
    Integer id;

    @Column(name = "dish_menu_id", columnDefinition = "INT")
    Integer orderedDishId;

    @Column(name = "ingredient_id", columnDefinition = "INT")
    Integer ingredientId;

}
