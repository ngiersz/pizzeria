package com.put.bd.pizzeria.domain.ingredient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@IdClass(BasicIngredientPK.class)
@Table(name = "basic_ingredient")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BasicIngredient {

    @Id
    @Column(name = "dish_menu_id", columnDefinition = "INT")
    Integer dishMenuId;

    @Id
    @Column(name = "ingredient_id", columnDefinition = "INT")
    Integer ingredientId;

}
