package com.put.bd.pizzeria.service.ingredient;

import com.put.bd.pizzeria.domain.ingredient.BasicIngredient;
import com.put.bd.pizzeria.domain.ingredient.Ingredient;
import com.put.bd.pizzeria.persistance.ingredient.BasicIngredientsRepository;
import com.put.bd.pizzeria.persistance.ingredient.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    BasicIngredientsRepository basicIngredientRepository;

//    @Autowired
//    AdditionalIngredientRepository additionalIngredientRepository;

    public List<Ingredient> getAll() {
        return ingredientRepository.findAll();
    }

    public List<BasicIngredient> getAllBasic() {
        return basicIngredientRepository.findAll();
    }



//    public Ingredient get(Integer id) {
//        return ingredientRepository.getOne(id);
//    }

//    public List<Ingredient> getDishIngredients(Integer dishMenuId) {
//        List<BasicIngredient> basicIngredients = basicIngredientRepository.getByDishMenuId(dishMenuId);
//        List<Ingredient> ingredients = new ArrayList<>();
//        for (BasicIngredient :
//             ) {
//
//        }
//    }
}
