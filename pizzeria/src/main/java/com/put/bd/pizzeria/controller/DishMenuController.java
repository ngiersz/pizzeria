package com.put.bd.pizzeria.controller;

import com.put.bd.pizzeria.domain.DishMenu;
import com.put.bd.pizzeria.service.DishMenuService;
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
    DishMenuService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<DishMenu> getAll() {
        return service.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public DishMenu get(@PathVariable(value = "id") Integer id) {
        return service.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable(value = "id") Integer id, @RequestBody DishMenu dishMenu)  {
        service.updateNameAndPrice(id, dishMenu);
    }

}
