package com.put.bd.pizzeria.persistance;

import com.put.bd.pizzeria.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
