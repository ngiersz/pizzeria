package com.put.bd.pizzeria.persistance.ingredient;

import com.put.bd.pizzeria.domain.ingredient.BasicIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicIngredientRepository extends JpaRepository<BasicIngredient, Integer> {

}
