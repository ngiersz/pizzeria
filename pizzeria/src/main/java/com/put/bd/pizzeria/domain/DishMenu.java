package com.put.bd.pizzeria.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.put.bd.pizzeria.domain.ingredient.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dish_menu")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DishMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "name", columnDefinition = "NVARCHAR(100)")
    @NotNull(message = "Nie podano nazwy dania.")
    String name;

    @Column(name = "price", columnDefinition = "MONEY")
    @NotNull(message = "Nie podano ceny dania.")
    BigDecimal price;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "basic_ingredient",
            joinColumns = { @JoinColumn(name = "dish_menu_id") },
            inverseJoinColumns = { @JoinColumn(name = "ingredient_id") })
    @NotNull(message = "Nie wybrano podstawowych składników dania.")
    private Set<Ingredient> basicIngredients = new HashSet<>();


    public DishMenu(Integer id, DishMenu dishMenu) {
        this.id = id;
        this.name = dishMenu.name;
        this.price = dishMenu.price;
    }

}
