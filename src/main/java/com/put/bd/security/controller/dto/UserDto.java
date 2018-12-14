package com.put.bd.security.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import com.put.bd.pizzeria.domain.User;

@AllArgsConstructor
@Getter
public class UserDto {

    private String login;
    private User.Role role;

    public UserDto(User user) {
        login = user.getLogin();
        role = user.getRole();
    }

}
