package com.put.bd.logging;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggingRepository extends MongoRepository<Log, String> {

}
