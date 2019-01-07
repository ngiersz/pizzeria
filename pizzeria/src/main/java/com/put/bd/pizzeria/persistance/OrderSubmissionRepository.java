package com.put.bd.pizzeria.persistance;

import com.put.bd.pizzeria.domain.OrderSubmission;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderSubmissionRepository extends MongoRepository<OrderSubmission, Integer> {
}
