package com.put.bd.pizzeria.persistance;

import com.put.bd.pizzeria.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    List<Client> findByFirstNameAndLastName(String firstName, String lastName);

}
