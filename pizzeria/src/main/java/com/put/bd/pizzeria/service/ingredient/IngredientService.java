package com.put.bd.pizzeria.service.ingredient;

import com.put.bd.pizzeria.domain.ingredient.Ingredient;
import com.put.bd.pizzeria.persistance.ingredient.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    @Autowired
    IngredientRepository ingredientRepository;

    public List<Ingredient> getAll() {
        return ingredientRepository.findAll();
    }

    public Ingredient get(Integer id) {
        System.out.println("ingredient id = " + id);
        return ingredientRepository.findById(id).get();
    }

}
