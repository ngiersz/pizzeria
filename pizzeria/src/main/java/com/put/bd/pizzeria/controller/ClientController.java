package com.put.bd.pizzeria.controller;

import com.put.bd.pizzeria.domain.Client;
import com.put.bd.pizzeria.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@CrossOrigin(origins = "*")
@Slf4j
public class ClientController {

    @Autowired
    ClientService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<Client> getAll() {
        return service.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Client get(@PathVariable(value = "id") Integer id) {
        // Exception?
        return service.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") Integer id) {
        service.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Integer create(@RequestBody Client client) throws Exception {
        try {
        return service.create(client);
        } finally {
            log.debug("New client was created");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable(value = "id") Integer id, @RequestBody Client client) throws Exception {
        service.update(id, client);
    }

}
