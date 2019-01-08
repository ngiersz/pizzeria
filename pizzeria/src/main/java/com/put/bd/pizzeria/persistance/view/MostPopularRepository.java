package com.put.bd.pizzeria.persistance.view;

import com.put.bd.pizzeria.domain.query.MostPopular;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MostPopularRepository extends JpaRepository<MostPopular, Integer> {
}
