package com.put.bd.pizzeria.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.put.bd.pizzeria.domain.ingredient.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ordered_dish")
public class OrderedDish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "order_id")
    @JsonIgnore
    Order order;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dish_menu_id")
    DishMenu dishMenuId;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "additional_ingredient",
            joinColumns = { @JoinColumn(name = "ordered_dish_id") },
            inverseJoinColumns = { @JoinColumn(name = "ingredient_id") })
    private Set<Ingredient> additionalIngredients = new HashSet<>();

    public OrderedDish(Integer orderId, Integer dishMenuId) throws NotImplementedException {
//        this.orderId = orderId;
//        this.dishMenuId = dishMenuId;
        throw new NotImplementedException();
    }

}
