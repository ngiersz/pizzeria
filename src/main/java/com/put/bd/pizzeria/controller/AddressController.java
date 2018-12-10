package com.put.bd.pizzeria.controller;

import com.put.bd.pizzeria.domain.Address;
import com.put.bd.pizzeria.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
@CrossOrigin(origins = "*")
@Slf4j
public class AddressController {

    @Autowired
    AddressService service;

    @Autowired
    RestResponseEntityExceptionHandler exceptionHandler;

    @RequestMapping(method = RequestMethod.GET)
    public List<Address> getAll() {
        return service.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Address get(@PathVariable(value = "id") Integer id) {
        return service.get(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Integer create(@RequestBody Address address) {
        try {
            return service.create(address);
        } /*catch (SQLException e) {
            return exceptionHandler.handleException(e);
        } */ finally {
            log.debug("New address was created");
        }
    }

}
