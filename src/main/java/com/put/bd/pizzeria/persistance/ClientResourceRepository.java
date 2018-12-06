package com.put.bd.pizzeria.persistance;

import com.put.bd.pizzeria.domain.Client;
import com.put.bd.pizzeria.persistance.ClientRepository;
import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryBase;
import io.katharsis.repository.ResourceRepositoryV2;
import io.katharsis.resource.list.ResourceList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@CrossOrigin(origins = "*")
public class ClientResourceRepository extends ResourceRepositoryBase<Client, Integer>
{
    private ClientRepository clientRepository;

    private Map<Integer, Client> clients = new HashMap<>();

    public ClientResourceRepository()
    {
        super(Client.class);

        clients.put(1, new Client(1, "Marcin", "asd","asfas","Asdsd",1,"asdsa",11));

        /*List<Client> clientsList = clientRepository.findAll();
        for (Client client : clientsList)
        {
            clients.put(client.getId(), client);
        }*/

    }

    @Override
    public Class<Client> getResourceClass()
    {
        return Client.class;
    }

    @Override
    public Client findOne(Integer id, QuerySpec querySpec)
    {
        return clientRepository.findById(id).get();
    }


    @Override
    public ResourceList<Client> findAll(QuerySpec querySpec)
    {
        return querySpec.apply(clients.values());
    }

}
