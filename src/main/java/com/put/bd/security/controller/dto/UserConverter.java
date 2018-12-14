package com.put.bd.security.controller.dto;

import com.put.bd.pizzeria.domain.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserConverter implements Converter<List<User>, List<UserDto>> {

    @Override
    public List<UserDto> convert(List<User> users) {
        return users.stream().map(u -> new UserDto(u)).collect(Collectors.toList());
    }

}
