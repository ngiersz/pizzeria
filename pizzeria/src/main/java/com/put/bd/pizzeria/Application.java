package com.put.bd.pizzeria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages ={"com.put.bd.pizzeria.persistance", "com.put.bd.logging"})
@ComponentScan(basePackages = {"com.put.bd.pizzeria", "com.put.bd.logging"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
