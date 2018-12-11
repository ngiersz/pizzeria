package com.put.bd.pizzeria.service;

import com.put.bd.pizzeria.domain.DishMenu;
import com.put.bd.pizzeria.domain.Order;
import com.put.bd.pizzeria.domain.OrderedDish;
import com.put.bd.pizzeria.persistance.DishMenuRepository;
import com.put.bd.pizzeria.persistance.OrderRepository;
import com.put.bd.pizzeria.persistance.OrderedDishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
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

//    @Autowired
//    Connection con;

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Order get(Integer id) {
        return orderRepository.getOne(id);
    }

    public List<Order> getClientsOrders(Integer clientId) {
        return orderRepository.findByClientId(clientId);
    }

    public Integer save(Integer clientId, List<DishMenu> orderedDishes) {
        Integer orderId = createNewOrder(clientId);
        System.out.println("Order id: " + orderId);
        for (DishMenu dish : orderedDishes) {
            OrderedDish orderedDish = new OrderedDish(orderId, dish.getId());
            orderedDishRepository.save(orderedDish);
        }
        return orderId;
    }

    // TODO: pobieranie orderID z bd
    private Integer createNewOrder(Integer clientId){
        Order order = new Order(clientId);
        return orderRepository.save(order).getId();
//        CallableStatement cStmt = con.prepareCall("EXEC F_add_order");
//        cStmt.registerOutParameter(1,Types.INTEGER);
//        cStmt.execute();
//        return cStmt.getInt(1);
    }

    public void delete(Integer id) {
        orderRepository.delete(orderRepository.getOne(id));
    }

}
