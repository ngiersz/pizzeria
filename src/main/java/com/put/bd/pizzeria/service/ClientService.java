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

    public ClientService(ClientRepository repository) {
        this.repository = repository;
        repository.save(new Client(1, "Jakub Piotr", "Hamerliński", "123456789", "jh@gmail.com", 1, "jh1"));
        repository.save(new Client(2, "Marcin", "Hradowicz", "123456789", "mh@gmail.com", 1, "mh2"));
        repository.save(new Client(3, "Natalia", "Gierszewska", "123456789", "ng@gmail.com", 1, "ng3"));
    }

    public List<Client> getAll() {
        return repository.findAll();
    }

    public Client get(Integer id) {
        Optional<Client> event;
        if((event = repository.findById(id)).isPresent()) {
            return event.get();
        }
        throw new EntityNotFoundException("Client id " + id + " doesn't exist");
    }

    public void update(Integer id, Client client) {
        Optional<Client> client1 = repository.findById(id);
        if(client1.isPresent()) {
            repository.save(new Client(id, client));
        } else {
            throw new EntityNotFoundException("Client id " + id + " doesn't exist");
        }
    }

    public Integer create(Client client) {
        return repository.save(client).getId();
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
