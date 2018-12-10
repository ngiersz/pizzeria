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

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Order get(Integer id) {
        return orderRepository.getOne(id);
    }

    public List<Order> getClientsOrders(Integer clientId) {
        return orderRepository.findByClientId(clientId);
    }

    // TODO: zapisywanie orderow
    public Integer save(Integer clientId, List<DishMenu> orderedDishes) {
        Integer orderId = createNewOrder(clientId);

        for (DishMenu dish : orderedDishes) {
            OrderedDish orderedDish = new OrderedDish(orderId, dish.getId());
            System.out.println("Ordered dish: ");
            System.out.println(dish.getId() + " " + dish.getName() + "; orderId: " + orderId);
            orderedDishRepository.save(orderedDish);
        }
        return orderId;
    }

    // TODO: pobieranie id zapisanego orderu z bd
    private Integer createNewOrder(Integer clientId) {
//        Order order = Order.builder().clientId(45).cookId(1).delivererId(1).completed(false).discount(0).deliveryTime(10).build();
//        String query = "EXEC insert_client '";
//        try {
//            jdbcTemplate.execute(query);
//        } catch (DataAccessException e) {
//            System.out.println("Cannot add new client. " + e.getMostSpecificCause());
//        }
        return 32;
    }

    public void delete(Integer id) {
        orderRepository.delete(orderRepository.getOne(id));
    }

}
