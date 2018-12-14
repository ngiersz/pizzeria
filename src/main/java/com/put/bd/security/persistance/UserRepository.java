package com.put.bd.security.persistance;

import com.put.bd.pizzeria.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
