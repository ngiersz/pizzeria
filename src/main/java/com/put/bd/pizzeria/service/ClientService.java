package com.put.bd.pizzeria.service;

import com.put.bd.pizzeria.domain.Client;
import com.put.bd.pizzeria.persistance.ClientRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

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

    public Long create(Client client) {
        return repository.save(client).getId();
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
