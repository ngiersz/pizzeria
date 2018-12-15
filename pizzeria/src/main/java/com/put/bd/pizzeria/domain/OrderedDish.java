package com.put.bd.pizzeria.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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

    @ManyToOne(targetEntity = Order.class)
    @JoinColumn(name = "order_id")
    Order order;

    @ManyToOne(targetEntity = DishMenu.class)
    @JoinColumn(name = "dish_menu_id")
    Integer dishMenuId;

    public OrderedDish(Integer orderId, Integer dishMenuId) throws NotImplementedException {
//        this.orderId = orderId;
//        this.dishMenuId = dishMenuId;
        throw new NotImplementedException();
    }

}
