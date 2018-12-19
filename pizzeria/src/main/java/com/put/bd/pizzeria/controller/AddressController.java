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
        } finally {
            log.debug("New address was created");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable(value = "id") Integer id, @RequestBody Address address) {
        service.update(id, address);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") Integer id) {
        service.delete(id);
    }

}
