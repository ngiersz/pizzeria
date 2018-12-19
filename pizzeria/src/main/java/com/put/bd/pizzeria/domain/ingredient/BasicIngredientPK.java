package com.put.bd.pizzeria.domain.ingredient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BasicIngredientPK implements Serializable {

    Integer dishMenuId;

    Integer ingredientId;
}
