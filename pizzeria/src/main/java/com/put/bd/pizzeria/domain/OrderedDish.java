package com.put.bd.pizzeria.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    @Column(name = "order_id")
    Integer orderId;

    @Column(name = "dish_menu_id")
    Integer dishMenuId;

    public OrderedDish(Integer orderId, Integer dishMenuId) {
        this.orderId = orderId;
        this.dishMenuId = dishMenuId;
    }

}
