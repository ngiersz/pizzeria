package com.put.bd.pizzeria.service.ingredient;

import com.put.bd.pizzeria.domain.ingredient.AdditionalIngredient;
import com.put.bd.pizzeria.domain.ingredient.BasicIngredient;
import com.put.bd.pizzeria.domain.ingredient.Ingredient;
import com.put.bd.pizzeria.persistance.ingredient.AdditionalIngredientRepository;
import com.put.bd.pizzeria.persistance.ingredient.BasicIngredientRepository;
import com.put.bd.pizzeria.persistance.ingredient.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    AdditionalIngredientRepository additionalIngredientRepository;

    @Autowired
    BasicIngredientRepository basicIngredientRepository;

    public List<Ingredient> getAll() {
        return ingredientRepository.findAll();
    }

    public Ingredient get(Integer id) {
        System.out.println("ingredient id = " + id);
        return ingredientRepository.findById(id).get();
    }

    public List<BasicIngredient> getAllBasic() {
        return basicIngredientRepository.findAll();
    }

    public List<AdditionalIngredient> getAllAdditional() {
        return additionalIngredientRepository.findAll();
    }
}
