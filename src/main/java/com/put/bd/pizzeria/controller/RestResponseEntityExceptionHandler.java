package com.put.bd.pizzeria.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;
import java.sql.SQLException;

//@ControllerAdvice
@Service
@Slf4j
public class RestResponseEntityExceptionHandler {

//    @ExceptionHandler({ SQLException.class, EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception e) {
//        Exception newException = new Exception(e.getMessage());
//        log.error(e.getMessage());
//        throw newException;
        return e.getMessage();
    }

}
