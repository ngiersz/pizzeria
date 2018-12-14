package com.put.bd.pizzeria.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

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

    @Column(name = "last_modification", columnDefinition = "INT")
    Integer lastModification;

    @Column(name = "quantity", columnDefinition = "INT")
    Integer quantity;

    public DishMenu(Integer id, DishMenu dishMenu) {
        this.id = id;
        this.name = dishMenu.name;
        this.price = dishMenu.price;
        this.quantity = dishMenu.quantity;
        this.lastModification = dishMenu.lastModification + 1;
    }

    public DishMenu(Integer id, String name, BigDecimal price, DishMenu dishMenu) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = dishMenu.quantity;
        this.lastModification = dishMenu.lastModification;
    }
}
