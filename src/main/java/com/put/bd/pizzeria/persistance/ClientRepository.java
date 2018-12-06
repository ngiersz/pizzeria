package com.put.bd.pizzeria.persistance;

import com.put.bd.pizzeria.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;


public interface ClientRepository extends JpaRepository<Client, Integer> {

}
