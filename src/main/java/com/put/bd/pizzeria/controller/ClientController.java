package com.put.bd.pizzeria.controller;

import com.put.bd.pizzeria.domain.Client;
import com.put.bd.pizzeria.service.ClientService;
import com.put.bd.pizzeria.utils.JsonConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

@RestController
@RequestMapping("/clients")
@CrossOrigin(origins = "*") // ip Kuby
@Slf4j
public class ClientController {

    @Autowired
    ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getAll() {
        String result = "{\"Clients\":[\n";
        for (Client client : service.getAll()) {
               result += client.toString() + "\n,";
        }
        result = result.substring(0, result.length()-2) + "\n]}";
        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String get(@PathVariable(value = "id") Integer id) {
        return service.get(id).toString();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") Integer id) {
        service.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Integer create(@RequestBody String clientStr) throws SQLException, IOException {
        Client client = (Client) JsonConverter.jsonToClassObject(clientStr, Client.class);
        try {
            return service.create(client);
        } finally {
            log.debug("New client was created");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable(value = "id") Integer id,@RequestBody String clientStr) throws SQLException, IOException {
        Client client = (Client) JsonConverter.jsonToClassObject(clientStr, Client.class);
        service.update(id, client);
    }

    @ExceptionHandler({ SQLException.class, EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleException(Exception e) throws Exception {
        Exception newException = new Exception(e.getMessage());
        System.out.println("new Exception(e.getMessage()).toString(): " + newException.toString());
        log.error(e.getMessage());
        throw newException;
    }

}
