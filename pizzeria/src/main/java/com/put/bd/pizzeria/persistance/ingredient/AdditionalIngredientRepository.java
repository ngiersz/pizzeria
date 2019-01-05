package com.put.bd.pizzeria.persistance.ingredient;

import com.put.bd.pizzeria.domain.ingredient.AdditionalIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalIngredientRepository extends JpaRepository<AdditionalIngredient, Integer> {

}
