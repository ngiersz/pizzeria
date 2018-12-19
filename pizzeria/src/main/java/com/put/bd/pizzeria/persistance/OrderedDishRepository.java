package com.put.bd.pizzeria.persistance;

import com.put.bd.pizzeria.domain.OrderedDish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderedDishRepository extends JpaRepository<OrderedDish, Integer> {

    List<OrderedDish> findAllByOrderId(Integer orderId);
}
