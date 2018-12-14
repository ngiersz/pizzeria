package com.put.bd.security.service;

import com.put.bd.pizzeria.domain.User;
import com.put.bd.security.persistance.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository repository;
    private PasswordEncoder passwordEncoder;
    private PasswordGenerator passwordGenerator;

    private SecurityUtil securityUtil;

    @Autowired
    public UserService(UserRepository repository, PasswordEncoder passwordEncoder, PasswordGenerator passwordGenerator) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.passwordGenerator = passwordGenerator;
        this.securityUtil = new SecurityUtil();
    }

    public User get(String username) {
        if(isUserInRepository(username) && hasAccessToUser(username)) {
            Optional<User> user = repository.findById(username);
            if(!user.isPresent())
                throw new EntityNotFoundException();
            else
                return user.get();
        }
        else
            throw new AccessDeniedException("Invalid username");

    }

    public String create(User user) {
        if(securityUtil.isCurrentUserAdmin()) {
            user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
            return repository.save(user).getLogin();
        }
        else
            throw new  AccessDeniedException("Not an admin");
    }

    public void delete(String username) {
        if(isUserInRepository(username) && hasAccessToUser(username))
            repository.delete(repository.getOne(username));
        else
            throw new AccessDeniedException("Not an admin");
    }

    public String resetPassword(String username) {
        if(securityUtil.isCurrentUserAdmin()) {
            Optional<User> user = repository.findById(username);
            if(!user.isPresent())
                throw new EntityNotFoundException();
            else {
                String newPassword = passwordGenerator.generatePassword(16);
//                user.setPasswordHash(newPassword);
                user.get().setPasswordHash(passwordEncoder.encode(newPassword));
                repository.save(user.get());
                return newPassword;
            }
        }
        else
            throw new AccessDeniedException("Not an admin");
    }

    public void updatePassword(String username, String password) {
        if(isUserInRepository(username) && isCurrentUserOwnerOfAccount(username)) {
            User user = repository.findById(username).get();
            repository.save(new User(username, passwordEncoder.encode(password), user.getRole()));
        }
        else
            throw new AccessDeniedException("Invalid username or not an owner");
    }

    private boolean isUserInRepository(String username) {
        Optional<User> user = repository.findById(username);
        if(!user.isPresent())
            throw new EntityNotFoundException();
        else
            return true;
    }

    private boolean hasAccessToUser(String username) {
        return securityUtil.isCurrentUserAdmin() || isCurrentUserOwnerOfAccount(username);
    }

    private boolean isCurrentUserOwnerOfAccount(String username){
        return securityUtil.isThisUserCurrentlyLogged(username);
    }
}
