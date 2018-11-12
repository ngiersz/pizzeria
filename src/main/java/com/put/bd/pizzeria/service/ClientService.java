package com.put.bd.pizzeria.service;

import com.put.bd.pizzeria.domain.Client;
import com.put.bd.pizzeria.persistance.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository repository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Client> getAll() {
        return repository.findAll();
    }

    public Client get(Long id) {
        Optional<Client> event;
        if((event = repository.findById(id)).isPresent()) {
            return event.get();
        }
        throw new EntityNotFoundException();
    }

    public void update(Long id, Client client) {
        Optional<Client> clientNotUpdated = repository.findById(id);
        if(clientNotUpdated.isPresent()) {
            repository.save(new Client(clientNotUpdated.get().getId(), client));
        } else {
            throw new EntityNotFoundException();
        }
    }

    public Long create(Client client) throws SQLException {
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

    public void delete(Long id) {
        Optional<Client> event = repository.findById(id);
        if(event.isPresent()) {
            repository.delete(event.get());
        } else {
            throw new EntityNotFoundException();
        }
    }

}
