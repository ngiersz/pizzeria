package com.put.bd.pizzeria.domain;

import com.put.bd.pizzeria.domain.ingredient.BasicIngredient;
import com.put.bd.pizzeria.domain.ingredient.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dish_menu")
public class DishMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "name", columnDefinition = "NVARCHAR(100)")
    String name;

    @Column(name = "price", columnDefinition = "MONEY")
    BigDecimal price;

    @OneToMany(targetEntity = BasicIngredient.class)
    @JoinColumn(name = "dish_menu_id")
    List<BasicIngredient> ingredients;

    public DishMenu(Integer id, DishMenu dishMenu) {
        this.id = id;
        this.name = dishMenu.name;
        this.price = dishMenu.price;
    }

}
