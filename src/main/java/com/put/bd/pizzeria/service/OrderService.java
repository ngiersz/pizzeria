package com.put.bd.pizzeria.service;

import com.put.bd.pizzeria.domain.Client;
import com.put.bd.pizzeria.domain.DishMenu;
import com.put.bd.pizzeria.domain.Order;
import com.put.bd.pizzeria.domain.OrderedDish;
import com.put.bd.pizzeria.persistance.OrderRepository;
import com.put.bd.pizzeria.persistance.OrderedDishRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

@Service
@Slf4j
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderedDishRepository orderedDishRepository;

    @Autowired
    DishMenuService dishMenuService;

    @Autowired
    ClientService clientService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Order get(Integer id) {
        return orderRepository.getOne(id);
    }

    public List<Order> getClientsOrders(Integer clientId) {
        return orderRepository.findByClientId(clientId);
    }

    @Transactional(rollbackOn = {OrderException.class, SQLException.class, Exception.class})
    public Integer save(Integer clientId, List<DishMenu> orderedDishes) throws Exception {
        Integer orderId = createNewOrder(clientId);
        increaseClientsNumberOfOrders(clientId);
        addOrderedDishes(orderId, orderedDishes);
        return orderId;
    }

    private void increaseClientsNumberOfOrders(Integer clientId) throws SQLException {
        Client client = clientService.get(clientId);
        client.setAmountOfOrders(client.getAmountOfOrders()+1);
        clientService.update(clientId, client);
        System.out.println("updated client: " + clientService.get(clientId).toString());
    }

    private void addOrderedDishes(Integer orderId, List<DishMenu> orderedDishes) throws Exception {
        for (DishMenu dish : orderedDishes) {

            DishMenu tempDish = dishMenuService.get(dish.getId());
            if(tempDish.getQuantity()<1) {
                throw new OrderException("Dish " + tempDish.getName() + " is not available");
            }
            OrderedDish orderedDish = new OrderedDish(orderId, dish.getId());
            orderedDishRepository.save(orderedDish);
            decreaseDishMenuQuantity(dish.getId(), dish);
        }
    }

    private void decreaseDishMenuQuantity(Integer id, DishMenu dish) throws Exception {
        DishMenu dishH = dishMenuService.get(id);
        dishH.setQuantity(dishH.getQuantity()-1);
        dishMenuService.update(id, dish);
    }

    // TODO: pobieranie orderID z bd
    private Integer createNewOrder(Integer clientId){
        Order order = new Order(clientId);
        return orderRepository.save(order).getId();
//        CallableStatement cStmt = con.prepareCall("EXEC F_add_order");
//        cStmt.registerOutParameter(1,Types.INTEGER);
//        cStmt.execute();
//        return cStmt.getInt(1);
    }

    public void delete(Integer id) {
        orderRepository.delete(orderRepository.getOne(id));
    }

}
