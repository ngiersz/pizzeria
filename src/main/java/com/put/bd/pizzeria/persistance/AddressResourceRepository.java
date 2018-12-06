package com.put.bd.pizzeria.persistance;

import com.put.bd.pizzeria.domain.Address;
import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryV2;
import io.katharsis.resource.list.ResourceList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressResourceRepository implements ResourceRepositoryV2<Address, Integer>
{

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Class<Address> getResourceClass()
    {
        return Address.class;
    }

    @Override
    public Address findOne(Integer id, QuerySpec querySpec)
    {
        return addressRepository.findById(id).get();
    }

    @Override
    public ResourceList<Address> findAll(QuerySpec querySpec)
    {
        return querySpec.apply(addressRepository.findAll());
    }

    @Override
    public ResourceList<Address> findAll(Iterable<Integer> ids, QuerySpec querySpec)
    {
        return querySpec.apply(addressRepository.findAllById(ids));
    }

    @Override
    public <S extends Address> S save(S entity)
    {
        return addressRepository.save(entity);
    }

    @Override
    public <S extends Address> S create(S entity)
    {
        return save(entity);
    }

    @Override
    public void delete(Integer id)
    {
        delete(id);
    }
}
