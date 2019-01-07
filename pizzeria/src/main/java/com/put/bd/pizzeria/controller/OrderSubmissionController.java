package com.put.bd.pizzeria.controller;

import com.put.bd.pizzeria.domain.OrderSubmission;
import com.put.bd.pizzeria.persistance.OrderSubmissionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordersubmissions")
@CrossOrigin(origins = "*")
@Slf4j
public class OrderSubmissionController {

    @Autowired
    OrderSubmissionRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public List<OrderSubmission> getAll() {
        return repository.findAll();
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public OrderSubmission get(@PathVariable(value = "id") String id) {
//        return repository.findById(id).get()
//    }

}

