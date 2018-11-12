package com.put.bd.pizzeria.controller;

import com.put.bd.pizzeria.domain.Client;
import com.put.bd.pizzeria.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/clients")
@Slf4j
public class ClientController {

    @Autowired
    ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Client> getAll() {
        return service.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Client get(@PathVariable(value = "id") Long id) {
        return service.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody Client client) throws SQLException {
        try {
            return service.create(client);
        } finally {
            log.debug("New client was created");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable(value = "id") Long id, @RequestBody Client client) {
        service.update(id, client);
    }

    @ExceptionHandler({ SQLException.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleException(Exception e) throws Exception {
        log.error(e.getMessage());
        throw new Exception(e.getMessage());
    }

}
