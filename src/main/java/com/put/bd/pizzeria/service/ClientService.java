package com.put.bd.pizzeria.service;

import com.put.bd.pizzeria.controller.RestResponseEntityExceptionHandler;
import com.put.bd.pizzeria.domain.Client;
import com.put.bd.pizzeria.persistance.ClientRepository;
import com.put.bd.pizzeria.utils.JsonConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ClientService {

    @Autowired
    ClientRepository repository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    RestResponseEntityExceptionHandler exceptionHandler;

    public String getAll() {
        return JsonConverter.objectsListToJson(Collections.singletonList(repository.findAll()), "Clients");
    }

    public Client get(Integer id) {
        Optional<Client> event;
        if((event = repository.findById(id)).isPresent()) {
            return event.get();
        }
        throw new EntityNotFoundException("Client id " + id + " doesn't exist");
    }

    public void update(Integer id, Client client) throws SQLException {
        String query = "EXEC update_client " +
                id + ", '" +
                client.getFirstName() + "', '" +
                client.getLastName() + "', '" +
                client.getEmail() + "', '" +
                client.getPhoneNumber() + "', " +
                client.getAddressId();
        System.out.println(query);

        try {
            jdbcTemplate.execute(query);
        } catch (DataAccessException e) {
            throw new SQLException("Cannot update client. " + e.getMostSpecificCause());
        }
    }

    public Integer create(Client client) throws SQLException {
        String query = "EXEC insert_client '" +
                client.getFirstName() + "', '" +
                client.getLastName() + "', '" +
                client.getEmail() + "', '" +
                client.getPhoneNumber() + "', " +
                client.getAddressId();
        try {
            jdbcTemplate.execute(query);
        } catch (DataAccessException e) {
            throw new SQLException("Cannot add new client. " + e.getMostSpecificCause());
        }
        return repository.findByFirstNameAndLastName(client.getFirstName(), client.getLastName()).get(0).getId();
    }

    public void delete(Integer id) throws EntityNotFoundException {
        Optional<Client> event = repository.findById(id);
        if(event.isPresent()) {
            repository.delete(event.get());
        } else {
            throw new EntityNotFoundException("Client id " + id + " doesn't exist");
        }
    }

}
