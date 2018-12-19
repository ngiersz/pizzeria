package com.put.bd.pizzeria.controller;

import com.put.bd.pizzeria.domain.ingredient.AdditionalIngredient;
import com.put.bd.pizzeria.domain.ingredient.BasicIngredient;
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

    @RequestMapping(value = "/basic", method = RequestMethod.GET)
    public List<BasicIngredient> getAllBasic() {
        return service.getAllBasic();
    }

    @RequestMapping(value = "/additional", method = RequestMethod.GET)
    public List<AdditionalIngredient> getAdditional() {
        return service.getAllAdditional();
    }

}
