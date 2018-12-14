package com.put.bd.security.controller;

import com.put.bd.pizzeria.domain.User;
import com.put.bd.security.controller.dto.UserConverter;
import com.put.bd.security.controller.dto.UserDto;
import com.put.bd.security.persistance.UserRepository;
import com.put.bd.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserConverter converter;

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        return converter.convert(users);
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public User get(@PathVariable("username") String username) {
        return userService.get(username);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody @Valid User user) {
        return userService.create(user);
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("username") String username) {
        userService.delete(username);
    }

    @RequestMapping(value = "/{username}/password", method = RequestMethod.PUT)
    public void update(@RequestBody @Valid String password, @PathVariable("username") String username) {
        userService.updatePassword(username, password);
    }

    @RequestMapping(value = "/{username}/password/reset", method = RequestMethod.PUT)
    public String reset(@PathVariable("username") String username) {
        return userService.resetPassword(username);
    }


    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String notFoundHandler(){
        return "nie znaleziono użytkownika o podanym loginie";
    }

}
