package com.put.bd.pizzeria.service;

import com.put.bd.pizzeria.domain.Order;
import com.put.bd.pizzeria.persistance.OrderRepository;
import com.put.bd.pizzeria.utils.JsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class OrderService {

    @Autowired
    OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public String getAll() {
        return JsonConverter.objectsListToJson(Collections.singletonList(repository.findAll()), "Orders");
    }

    public String get(Integer id) {
        Order order = repository.getOne(id);
        return JsonConverter.objectToJson(order);
    }

    public Order save(String orderStr) {
        Order order = (Order) JsonConverter.jsonToClassObject(orderStr, Order.class);
        System.out.println(orderStr);
        System.out.println(order.getId());
        System.out.println(order.getClientId());
        return repository.save(order);
    }

    public void update(Integer id, String orderStr) {
        Order updatedOrder = new Order(id, (Order) JsonConverter.jsonToClassObject(orderStr, Order.class));
        repository.save(updatedOrder);
    }
}
