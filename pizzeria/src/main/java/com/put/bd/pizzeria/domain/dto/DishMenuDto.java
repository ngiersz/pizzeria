package com.put.bd.pizzeria.domain.dto;

import com.put.bd.pizzeria.domain.ingredient.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class DishMenuDto {

    Integer id;
    String name;
    BigDecimal price;
    List<Ingredient> ingredients;
}
