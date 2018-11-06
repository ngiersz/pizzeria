package com.put.bd.pizzeria.service;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.put.bd.pizzeria.domain.Address;
import com.put.bd.pizzeria.domain.Client;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ClientService {

    private Map<Long, Client> repository;

    public ClientService() {
        repository = ImmutableMap.<Long, Client>builder()
                .put(1L, new Client(1L, "Marcin", "Hradowicz", "marcin@put.pl", "123456789"))
                .put(2L, new Client(2L, "Natalia", "Gierszewska", "natalia@put.pl", "000111222"))
                .put(3L, new Client(3L, "Jakub Piotr", "Hamerliński", "jakub.piotr@put.pl", "999888777"))
                .build();
    }

    public List<Client> getAll() {
        return Lists.newArrayList(repository.values());
    }
}
