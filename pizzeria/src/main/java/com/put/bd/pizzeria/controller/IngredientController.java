package com.put.bd.pizzeria.controller;

import com.put.bd.pizzeria.domain.ingredient.Ingredient;
import com.put.bd.pizzeria.service.ingredient.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/ingredients")
public class IngredientController {

    @Autowired
    IngredientService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<Ingredient> getAll() {
        return service.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Ingredient get(@PathVariable(value = "id") Integer id) {
        return service.get(id);
    }

}
