package com.put.bd.pizzeria.controller;

import com.put.bd.pizzeria.domain.Order;
import com.put.bd.pizzeria.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*")
@Slf4j
public class OrderController {

    @Autowired
    OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Order> getAll() {
        return service.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Order get(@PathVariable(value = "id") Integer id) {
        return service.get(id);
    }

    @RequestMapping(value = "/client/{id}", method = RequestMethod.GET)
    public List<Order> getClientOrders(@PathVariable(value = "id") Integer clientId) {
        return service.getClientsOrders(clientId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") Integer id) {
        service.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Integer create(@PathVariable(value = "id") Integer clientId, @RequestBody Order order) throws Exception {
        try {
            return service.save(clientId, order.getOrderedDishes());
        } finally {
            log.debug("New order was created");
        }
    }

    @ExceptionHandler({ EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleException(Exception e) throws Exception {
        log.error(e.getMessage());
        throw new Exception(e.getMessage());
    }

}
