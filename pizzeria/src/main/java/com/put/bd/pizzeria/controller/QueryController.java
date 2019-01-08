package com.put.bd.pizzeria.controller;

import com.put.bd.pizzeria.domain.Client;
import com.put.bd.pizzeria.domain.query.ClientStatistics;
import com.put.bd.pizzeria.domain.query.ClientValue;
import com.put.bd.pizzeria.domain.query.MostPopular;
import com.put.bd.pizzeria.persistance.view.ClientValueRepository;
import com.put.bd.pizzeria.persistance.view.MostPopularRepository;
import com.put.bd.pizzeria.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/query")
@CrossOrigin(origins = "*")
public class QueryController {

    @Autowired
    ClientValueRepository clientValueRepository;

    @Autowired
    DataSource dataSource;

    @Autowired
    ClientService clientService;

    @Autowired
    MostPopularRepository mostPopularRepository;

    @RequestMapping(value = "/clientValue", method = RequestMethod.GET)
    public List<ClientValue> clientValue() {
        return clientValueRepository.findAll();
    }

    @RequestMapping(value = "/clientStatistics/{id}", method = RequestMethod.GET)
    public ClientStatistics clientStatistics(@PathVariable(value = "id") Integer clientID) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("client_statistics");
        SqlParameterSource in = new MapSqlParameterSource().addValue("client_id", clientID);
        Map out = jdbcCall.execute(in);

        List rows = (ArrayList<ClientStatistics>) out.get("#result-set-1");
        String result = rows.get(0).toString();
        result = result.replaceAll("[^0-9]+", " ");
        List resultList = Arrays.asList(result.trim().split(" "));

        Client client = clientService.get(clientID);

        return ClientStatistics.builder()
                .overallValue(Integer.parseInt((String) resultList.get(0)))
                .valuePerOrder(Integer.parseInt((String) resultList.get(1)))
                .maxOrderVaue(Integer.parseInt((String) resultList.get(2)))
                .minOrderValue(Integer.parseInt((String) resultList.get(3)))
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .build();
    }

    @RequestMapping(value = "/mostPopularPizza", method = RequestMethod.GET)
    public MostPopular mostPopuarPizza() {
        return mostPopularRepository.findAll().get(0);
    }

    @RequestMapping(value = "/orderPrice/{id}", method = RequestMethod.GET)
    public Float orderPrice(@PathVariable(value = "id") Integer orderId) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("order_price");
        SqlParameterSource in = new MapSqlParameterSource().addValue("order_id", orderId);
        Map out = jdbcCall.execute(in);

        List rows = (ArrayList<ClientStatistics>) out.get("#result-set-1");
        String stringValue = rows.get(0).toString();

        Pattern regex = Pattern.compile("[0-9]*[.][0-9]*");
        Matcher m = regex.matcher(stringValue);

        Float result = null;
        if (m.find()) {
            result = Float.valueOf(m.group());
        }

        return result;
    }



}
