package com.put.bd.pizzeria.domain.ingredient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "additional_ingredient")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "ordered_dish_id", columnDefinition = "INT")
    Integer orderedDishId;

    @Column(name = "ingredient_id", columnDefinition = "INT")
    Integer ingredientId;

}
