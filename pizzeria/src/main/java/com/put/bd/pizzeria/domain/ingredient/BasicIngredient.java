package com.put.bd.pizzeria.domain.ingredient;

import com.put.bd.pizzeria.domain.DishMenu;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "basic_ingredient")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BasicIngredient {

    @Id
    Integer id;

//    @Column(name = "dish_menu_id", columnDefinition = "INT")
    @ManyToOne(targetEntity = Ingredient.class)
    @JoinColumn(name = "dish_menu_id")
    DishMenu dishMenu;

    @ManyToOne(targetEntity = Ingredient.class)
    @JoinColumn(name = "ingredient_id")
    Ingredient ingredient;

}
