package com.put.bd.pizzeria.service;

import com.put.bd.pizzeria.domain.DishMenu;
import com.put.bd.pizzeria.domain.Order;
import com.put.bd.pizzeria.domain.OrderedDish;
import com.put.bd.pizzeria.persistance.DishMenuRepository;
import com.put.bd.pizzeria.persistance.OrderRepository;
import com.put.bd.pizzeria.persistance.OrderedDishRepository;
import com.put.bd.pizzeria.utils.JsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderedDishRepository orderedDishRepository;

    @Autowired
    DishMenuRepository dishMenuRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public String getAll() {
        return JsonConverter.objectsListToJson(Collections.singletonList(orderRepository.findAll()), "Orders");
    }

    public String get(Integer id) {
        Order order = orderRepository.getOne(id);
        return JsonConverter.objectToJson(order);
    }

    public Integer save(String orderStr) {
        System.out.println("Dish Menu:");
        List<DishMenu> menu = dishMenuRepository.findAll();
        for (DishMenu dish : menu) {
            System.out.println(dish.getId() + " " + dish.getName());
        }

        System.out.println("orderStr: " + orderStr);
        List<DishMenu> orderedDishes = (List<DishMenu>)(List<?>) JsonConverter.jsonListToClassObjectList(orderStr, DishMenu[].class);
        System.out.println(orderedDishes.toString());

        Integer orderId = createNewOrder();

        for (DishMenu dish : orderedDishes) {
            OrderedDish orderedDish = new OrderedDish(orderId, dish.getId());
            System.out.println("Ordered dish: ");
            System.out.println(dish.getId() + " " + dish.getName() + "; orderId: " + orderId);
            orderedDishRepository.save(orderedDish);
        }
        return orderId;
    }

    private Integer createNewOrder() {
//        Order order = Order.builder().clientId(45).cookId(1).delivererId(1).completed(false).discount(0).deliveryTime(10).build();
//        String query = "EXEC insert_client '";
//        try {
//            jdbcTemplate.execute(query);
//        } catch (DataAccessException e) {
//            System.out.println("Cannot add new client. " + e.getMostSpecificCause());
//        }
        return 32;
    }

    public void update(Integer id, String orderStr) {
        Order updatedOrder = new Order(id, (Order) JsonConverter.jsonToClassObject(orderStr, Order.class));
        orderRepository.save(updatedOrder);
    }
}
