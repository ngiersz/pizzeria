package com.put.bd.pizzeria.controller;

import com.put.bd.pizzeria.domain.Address;
import com.put.bd.pizzeria.service.AddressService;
import com.put.bd.pizzeria.utils.JsonConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;

@RestController
@RequestMapping("/addresses")
@CrossOrigin(origins = "*") // ip Kuby
@Slf4j
public class AddressController {

    @Autowired
    AddressService service;

    @Autowired
    RestResponseEntityExceptionHandler exceptionHandler;

//    @RequestMapping(method = RequestMethod.GET)
//    public String get() {
//        return JsonConverter.objectsListToJson(Collections.singletonList(service.getAll()), "Addresses");
//    }

    @RequestMapping(/*value = "/{id}",*/ method = RequestMethod.GET)
    public String get(@RequestParam(value = "filter[id]") Integer id) {
        System.out.println("id = " + id);
        try {
//            return service.get(id);
            return "{\"Address\":[ " + JsonConverter.objectToJson(service.get(id)) + "]}";
        } catch (EntityNotFoundException e) {
            return exceptionHandler.handleException(e);
//            return null;
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody String addressStr) {
        System.out.println("Address POST: ");
        System.out.println(addressStr);
        Address address = (Address) JsonConverter.jsonToClassObject(addressStr, Address.class);
        try {
            return service.create(address);
        } /*catch (SQLException e) {
            return exceptionHandler.handleException(e);
        } */ finally {
            log.debug("New address was created");
        }
    }

}
