package com.put.bd.pizzeria.service;

import com.put.bd.logging.MongoDBLogger;
import com.put.bd.pizzeria.domain.Client;
import com.put.bd.pizzeria.persistance.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ClientService {

    @Autowired
    ClientRepository repository;

    @Autowired
    AddressService addressService;

    @Autowired
    MongoDBLogger logger;

    public List<Client> getAll() {
        return repository.findAll();
    }

    public Client get(Integer id) {
        Optional<Client> event;
        if((event = repository.findById(id)).isPresent()) {
            return event.get();
        }
        throw new EntityNotFoundException("Klient o id " + id + " nie istnieje");
    }

    public void update(Integer id, Client client) throws Exception {
        try {
            repository.save(new Client(id, client));
            logger.info("Zmodyfikowano klienta o id=" + id + ".");
        } catch (DataAccessException e) {
            throw new Exception("Nie można zaktualizować danych klienta o id " + id + ". " + e.getMostSpecificCause());
        }
    }

    public Integer create(Client client) throws Exception {
        if(client.getAddress().getId() == null) {
            Integer id = addressService.create(client.getAddress());
            logger.info("Dodano klienta o id=" + id + ".");
            return id;
        }
        try {
            return repository.save(client).getId();
        } catch (Exception e) {
            throw new Exception("Nie można dodać nowego klienta. " + e.getMessage());
        }
    }

    public void delete(Integer id) throws EntityNotFoundException {
        Optional<Client> event = repository.findById(id);
        if(event.isPresent()) {
            repository.delete(event.get());
            logger.info("Usunięto klienta o id=" + id + ".");
        } else {
            throw new EntityNotFoundException("Klient o id " + id + " nie istnieje");
        }
    }

}
