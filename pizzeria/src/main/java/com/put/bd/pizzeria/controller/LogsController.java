package com.put.bd.pizzeria.controller;

import com.put.bd.logging.Log;
import com.put.bd.logging.LoggingRepository;
import com.put.bd.pizzeria.domain.OrderSubmission;
import com.put.bd.pizzeria.persistance.OrderSubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class LogsController {

    @Autowired
    OrderSubmissionRepository orderSubmissionRepository;

    @Autowired
    LoggingRepository loggingRepository;

    @RequestMapping(value = "/ordersubmissions", method = RequestMethod.GET)
    public List<OrderSubmission> getAllOrderSubmissiosn() {
        return orderSubmissionRepository.findAll();
    }

    @RequestMapping(value = "/logs", method = RequestMethod.GET)
    public List<Log> getAllLogs() {
        return loggingRepository.findAll();
    }

}

