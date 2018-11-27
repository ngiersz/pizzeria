package com.put.bd.pizzeria.controller;

import com.put.bd.pizzeria.persistance.DishMenuRepository;
import com.put.bd.pizzeria.utils.JsonConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/dishMenus")
@CrossOrigin(origins = "*") // ip Kuby
@Slf4j
public class DishMenuController {

    @Autowired
    DishMenuRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public String getAll() {
        return JsonConverter.objectsListToJson(Collections.singletonList(repository.findAll()), "DishMenu");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String get(@PathVariable(value = "id") Integer id) {
        return JsonConverter.objectToJson(repository.findById(id));
    }



}
