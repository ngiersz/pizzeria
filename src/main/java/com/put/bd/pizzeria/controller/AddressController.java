package com.put.bd.pizzeria.controller;

import com.put.bd.pizzeria.service.AddressService;
import com.put.bd.pizzeria.utils.JsonConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/addresses")
@CrossOrigin(origins = "*") // ip Kuby
@Slf4j
public class AddressController {

    @Autowired
    AddressService service;

    @Autowired
    RestResponseEntityExceptionHandler exceptionHandler;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String get(@PathVariable(value = "id") Integer id) {
        try {
            return JsonConverter.objectToJson(service.get(id));
        } catch (EntityNotFoundException e) {
            return exceptionHandler.handleException(e);
        }
    }

}
