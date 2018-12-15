package com.put.bd.pizzeria.service;

import com.put.bd.pizzeria.domain.DishMenu;
import com.put.bd.pizzeria.persistance.DishMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class DishMenuService {

    @Autowired
    DishMenuRepository repository;


    public List<DishMenu> getAll() {
        return repository.findAll();
    }

    public DishMenu get(Integer id) {
        return repository.findById(id).get();
    }

    public Integer create(DishMenu dish) {
        return repository.save(dish).getId();
    }

    public void updateNameAndPrice(Integer id, DishMenu dishMenu) {
        DishMenu updatedDish = new DishMenu(id, dishMenu.getName(), dishMenu.getPrice(), get(id));
        repository.save(updatedDish);
    }

    @Transactional
    public void update(Integer id, DishMenu dish) throws Exception {
        DishMenu updatedDish = repository.findById(id).get();
        if(updatedDish == null ){
            throw new EntityNotFoundException("DishMenu " + id + " doesn't exist");
        }
        if (!dish.getLastModification().equals(updatedDish.getLastModification())) {
            throw new Exception("Cannot modify dish menu " + id + ". Data has changed. ");
        }
        dish.setQuantity(updatedDish.getQuantity());
        repository.save(new DishMenu(id, dish));
    }

}