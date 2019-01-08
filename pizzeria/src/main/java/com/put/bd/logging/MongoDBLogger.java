package com.put.bd.logging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MongoDBLogger {

    public LoggingRepository repository;

    @Autowired
    public MongoDBLogger(LoggingRepository repository) {
        this.repository = repository;
        if(this.repository == null) {
            System.out.println("REPO IS NULL");
        }
    }

    public void info(String message) {
        Log log = new Log(null, LocalDateTime.now(), "INFO: " + message);
        repository.save(log);
    }

    public void error(String message) {
        repository.save(new Log(null, LocalDateTime.now(), "ERROR: " + message));
    }
}
