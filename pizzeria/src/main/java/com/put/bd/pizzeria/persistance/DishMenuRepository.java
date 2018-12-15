package com.put.bd.pizzeria.persistance;

import com.put.bd.pizzeria.domain.DishMenu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishMenuRepository extends JpaRepository<DishMenu, Integer> {

}
