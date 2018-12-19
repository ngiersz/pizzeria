package com.put.bd.pizzeria.service;

import com.put.bd.pizzeria.domain.Client;
import com.put.bd.pizzeria.domain.Order;
import com.put.bd.pizzeria.domain.OrderedDish;
import com.put.bd.pizzeria.domain.ingredient.AdditionalIngredient;
import com.put.bd.pizzeria.domain.ingredient.Ingredient;
import com.put.bd.pizzeria.persistance.OrderRepository;
import com.put.bd.pizzeria.persistance.OrderedDishRepository;
import com.put.bd.pizzeria.persistance.ingredient.AdditionalIngredientRepository;
import com.put.bd.pizzeria.persistance.ingredient.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderedDishRepository orderedDishRepository;

    @Autowired
    AdditionalIngredientRepository  additionalIngredientRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    ClientService clientService;

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Order get(Integer id) {
        return orderRepository.findById(id).get();
    }

    public List<Order> getClientsOrders(Integer clientId) {
        return orderRepository.findByClientId(clientId);
    }

    @Transactional(rollbackOn = {OrderException.class, SQLException.class, Exception.class})
    public Integer save(Integer clientId, Set<OrderedDish> orderedDishes) throws Exception {
        Client client = clientService.get(clientId);
        Integer orderId = createNewOrder(client);
        increaseClientsNumberOfOrders(clientId);
        addOrderedDishes(orderId, orderedDishes);
        return orderId;
    }

    private void increaseClientsNumberOfOrders(Integer clientId) throws Exception {
        Client client = clientService.get(clientId);
        client.setAmountOfOrders(client.getAmountOfOrders()+1);
        clientService.update(clientId, client);
        System.out.println("updated client: " + clientService.get(clientId).toString());
    }

    private void addOrderedDishes(Integer orderId, Set<OrderedDish> orderedDishes) {
        for (OrderedDish dish : orderedDishes) {
            System.out.println("wysłane przez klienta: " + dish.getId() + " " + dish.getDishMenu() + " " + dish.getOrder() + " " + dish.getAdditionalIngredients());
            OrderedDish orderedDish = new OrderedDish(get(orderId), dish);
            System.out.println("dodawane do bazy danych: " + orderedDish.getId() + " " + orderedDish.getDishMenu() + " " + orderedDish.getOrder() + " " + orderedDish.getAdditionalIngredients());
            Set<Ingredient> createdAdditionalIngredients = saveAdditionalIngredients(orderedDish.getId(), orderedDish.getAdditionalIngredients());
            orderedDish.setAdditionalIngredients(createdAdditionalIngredients);
            orderedDishRepository.save(orderedDish);
        }
    }

    private Set<Ingredient> saveAdditionalIngredients(Integer orderedDishId, Set<Ingredient> ingredients) {
        Set<Ingredient> createdIngredients = new HashSet<>();
        for (Ingredient ingredient : ingredients) {
            AdditionalIngredient additionalIngredient = new AdditionalIngredient(null, orderedDishId, ingredient.getId());
            additionalIngredientRepository.save(additionalIngredient);
            createdIngredients.add(ingredientRepository.findById(additionalIngredient.getIngredientId()).get());
        }
        return createdIngredients;
    }

    private Integer createNewOrder(Client client){
        Order order = new Order(client);
        return orderRepository.save(order).getId();
    }

    public void delete(Integer id) {
        orderRepository.delete(orderRepository.getOne(id));
    }

    public List<OrderedDish> getOrderedDishes(Integer orderId) {
        return orderedDishRepository.findAllByOrderId(orderId);
    }
}
