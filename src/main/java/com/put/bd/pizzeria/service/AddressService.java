package com.put.bd.pizzeria.service;

import com.put.bd.pizzeria.controller.RestResponseEntityExceptionHandler;
import com.put.bd.pizzeria.domain.Address;
import com.put.bd.pizzeria.persistance.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

public class AddressService {

    @Autowired
    AddressRepository repository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    RestResponseEntityExceptionHandler exceptionHandler;


    public Address get(Integer id) {
        Optional<Address> event;
        if((event = repository.findById(id)).isPresent()) {
            return event.get();
        }
        throw new EntityNotFoundException("Address id " + id + " doesn't exist");
    }
}
