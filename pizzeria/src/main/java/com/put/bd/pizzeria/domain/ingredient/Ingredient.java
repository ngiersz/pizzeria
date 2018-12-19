package com.put.bd.pizzeria.domain.ingredient;

import com.put.bd.pizzeria.domain.DishMenu;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ingredient")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {

    @Id
    @Column(name = "id")
    Integer id;

    @Column(name = "name", columnDefinition = "NVARCHAR(100)")
    String name;

    @Column(name = "price", columnDefinition = "MONEY")
    BigDecimal price;

    @Column(name = "quantity_in_storeroom", columnDefinition = "INT")
    Integer quantity;

//    @ManyToMany(fetch = FetchType.LAZY,
//            cascade = {
//                    CascadeType.PERSIST,
//                    CascadeType.MERGE
//            },
//            mappedBy = "ingredients")
//    private Set<DishMenu> dishes = new HashSet<>();

}
