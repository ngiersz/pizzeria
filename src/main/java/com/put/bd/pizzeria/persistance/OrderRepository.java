package com.put.bd.pizzeria.persistance;

import com.put.bd.pizzeria.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByClientId(Integer clientId);
}
