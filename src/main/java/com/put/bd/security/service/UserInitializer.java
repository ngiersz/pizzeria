package com.put.bd.security.service;

import com.put.bd.pizzeria.domain.User;
import com.put.bd.security.persistance.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
@Slf4j
public class UserInitializer {

    @Autowired
    public UserInitializer(UserRepository repository, PasswordEncoder passwordEncoder){
        User admin = User.builder().login("admin")
                .passwordHash(passwordEncoder.encode("admin"))
                .role(User.Role.ADMIN)
                .build();
        User plain = User.builder().login("user")
                .passwordHash(passwordEncoder.encode("user"))
                .role(User.Role.USER)
                .build();
        Stream.of( admin, plain)
                .forEach(user -> {
                    repository.save(user);
                    log.info("user added: {}", user);
                });
    }


}
