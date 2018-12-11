package com.put.bd.pizzeria.controller;

import com.put.bd.pizzeria.domain.DishMenu;
import com.put.bd.pizzeria.persistance.DishMenuRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dishes")
@CrossOrigin(origins = "*")
@Slf4j
public class DishMenuController {

    @Autowired
    DishMenuRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public List<DishMenu> getAll() {
        return repository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public DishMenu get(@PathVariable(value = "id") Integer id) {
        return repository.findById(id).get();
    }

}
