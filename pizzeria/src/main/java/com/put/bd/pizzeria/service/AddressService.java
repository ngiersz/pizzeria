package com.put.bd.pizzeria.service;

import com.put.bd.logging.MongoDBLogger;
import com.put.bd.pizzeria.domain.Address;
import com.put.bd.pizzeria.persistance.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepository repository;

    @Autowired
    MongoDBLogger logger;

    public Address get(Integer id) {
        Optional<Address> event;
        if((event = repository.findById(id)).isPresent()) {
            return event.get();
        }
        throw new EntityNotFoundException("Adres o id=" + id + " nie istnieje");
    }

    public List<Address> getAll() {
        return repository.findAll();
    }

    public Integer create(Address address) {
        Integer id = repository.save(address).getId();
        logger.info("Dodano adres o id=" + id + ".");
        return id;
    }

    public void update(Integer id, Address address) {
        repository.save(new Address(id, address));
        logger.info("Zmodyfikowano adres o id=" + id + ".");
//        try {
//            repository.save(new Address(id, address));
//        } catch (DataAccessException e) {
//            throw new Exception("Nie można zaktualizować danych klienta o id " + id + ". " + e.getMostSpecificCause());
//        }
    }

    public void delete(Integer id) {
        repository.delete(repository.findById(id).get());
        logger.info("Usunięto adres o id=" + id + ".");
    }
}
