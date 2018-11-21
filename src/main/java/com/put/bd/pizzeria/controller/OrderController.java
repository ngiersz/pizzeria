package com.put.bd.pizzeria.controller;

import com.put.bd.pizzeria.domain.Order;
import com.put.bd.pizzeria.persistance.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*")
@Slf4j
public class OrderController {

    @Autowired
    OrderRepository repository;

    public OrderController(OrderRepository repository) {
        this.repository = repository;
    }

    // TODO
    @RequestMapping(method = RequestMethod.GET)
    public List<Order> getAll() {
        return repository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String get(@PathVariable(value = "id") Integer id) {
        return repository.findById(id).get().toString();
    }

    // TODO
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public void delete(@PathVariable(value = "id") Long id) {
//        service.delete(id);
//    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Integer create(@Valid @RequestBody Order order) {
        try {
            return repository.save(order).getId();
        } finally {
            log.debug("New client was created");
        }
    }

    // TODO
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable(value = "id") Integer id, @RequestBody Order order) {
        repository.save(order);
    }

    @ExceptionHandler({ EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleException(Exception e) throws Exception {
        log.error(e.getMessage());
        throw new Exception(e.getMessage());
    }


}
