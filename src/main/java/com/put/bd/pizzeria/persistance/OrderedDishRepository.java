package com.put.bd.pizzeria.persistance;

import com.put.bd.pizzeria.domain.OrderedDish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedDishRepository extends JpaRepository<OrderedDish, Integer> {

}
