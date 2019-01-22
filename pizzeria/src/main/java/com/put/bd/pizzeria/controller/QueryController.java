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
import java.util.*;
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

        String resultInt = result.replaceAll("[^0-9]+", " ");
        Integer matchedInt = Integer.parseInt(Arrays.asList(resultInt.trim().split(" ")).get(0));

        Pattern regex = Pattern.compile("[0-9]*[[0-9]+[.][0-9]*]{4}");
        Matcher m = regex.matcher(result);
        List<String> allMatches = new ArrayList<>();
        while (m.find()) {
            allMatches.add(m.group());
        }
        Client client = clientService.get(clientID);

        return ClientStatistics.builder()
                .overallValue(Float.valueOf(allMatches.get(0)))
                .valuePerOrder(Float.valueOf(allMatches.get(1)))
                .maxOrderValue(Float.valueOf(allMatches.get(2)))
                .minOrderValue(Float.valueOf(allMatches.get(3)))
                .numberOfOrders(matchedInt)
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .build();
    }

    @RequestMapping(value = "/mostPopularPizza", method = RequestMethod.GET)
    public List<MostPopular> mostPopularPizza() {
        return mostPopularRepository.findAll();
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
