package com.put.bd.pizzeria.controller;

import com.put.bd.pizzeria.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/dishLists")
@CrossOrigin(origins = "*")
@Slf4j
public class OrderController {

    @Autowired
    OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getAll() {
        return service.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String get(@PathVariable(value = "id") Integer id) {
        return service.get(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getClientOrders(@RequestParam(value = "id") Integer clientId) {
        return service.getClientsOrders(clientId);
    }

    // TODO
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public void delete(@PathVariable(value = "id") Long id) {
//        service.delete(id);
//    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Integer create(@RequestBody String orderStr) {
        try {
            return service.save(orderStr);
        } finally {
            log.debug("New order was created");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable(value = "id") Integer id, @RequestBody String orderStr) {
        service.update(id, orderStr);
    }

    @ExceptionHandler({ EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleException(Exception e) throws Exception {
        log.error(e.getMessage());
        throw new Exception(e.getMessage());
    }


}
