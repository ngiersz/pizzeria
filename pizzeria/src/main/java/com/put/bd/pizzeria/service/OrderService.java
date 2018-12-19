package com.put.bd.pizzeria.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.put.bd.pizzeria.domain.Client;
import com.put.bd.pizzeria.domain.DishMenu;
import com.put.bd.pizzeria.domain.Order;
import com.put.bd.pizzeria.domain.OrderedDish;
import com.put.bd.pizzeria.persistance.OrderRepository;
import com.put.bd.pizzeria.persistance.OrderedDishRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

@Service
@Slf4j
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderedDishRepository orderedDishRepository;

    @Autowired
    ClientService clientService;

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Order get(Integer id) {
        return orderRepository.findById(id).get();
    }

    public List<Order> getClientsOrders(Integer clientId) {
        return orderRepository.findByClientId(clientId);
    }

    @Transactional(rollbackOn = {OrderException.class, SQLException.class, Exception.class})
    public Integer save(Integer clientId, List<DishMenu> orderedDishes) throws Exception {
        Integer orderId = createNewOrder(clientId);
        increaseClientsNumberOfOrders(clientId);
        addOrderedDishes(orderId, orderedDishes);
        return orderId;
    }

    private void increaseClientsNumberOfOrders(Integer clientId) throws Exception {
        Client client = clientService.get(clientId);
        client.setAmountOfOrders(client.getAmountOfOrders()+1);
        clientService.update(clientId, client);
        System.out.println("updated client: " + clientService.get(clientId).toString());
    }

    private void addOrderedDishes(Integer orderId, List<DishMenu> orderedDishes) {
        for (DishMenu dish : orderedDishes) {
            OrderedDish orderedDish = new OrderedDish(orderId, dish.getId());
            orderedDishRepository.save(orderedDish);
        }
    }

    private Integer createNewOrder(Integer clientId){
        Order order = new Order(clientId);
        return orderRepository.save(order).getId();
    }

    public void delete(Integer id) {
        orderRepository.delete(orderRepository.getOne(id));
    }

    public List<OrderedDish> getOrderedDishes(Integer orderId) {
        return orderedDishRepository.findAllByOrderId(orderId);
    }
}
