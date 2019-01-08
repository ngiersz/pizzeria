package com.put.bd.pizzeria.persistance.view;

import com.put.bd.pizzeria.domain.query.ClientValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientValueRepository extends JpaRepository<ClientValue, Integer> {
}
