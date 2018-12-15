package com.put.bd.pizzeria.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "\"order\"")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "client_id")
    Integer clientId;

    @Column(name = "deliverer_id")
    Integer delivererId;

    @Column(name = "cook_id")
    Integer cookId;

    @Column(name = "delivery_time")
    Integer deliveryTime;

    @Column(name = "discount")
    Integer discount;

    @Column(name = "completed")
    Boolean completed;

    @Override
    public String toString() {
        return "{\n" +
                "  \"id\": \"" + this.id + "\",\n" +
                "  \"clientId\": \"" + this.clientId + "\",\n" +
                "  \"delivererId\": \"" + this.delivererId + "\",\n" +
                "  \"cookId\": \"" + this.cookId + "\",\n" +
                "  \"deliveryTime\": \"" + this.deliveryTime + "\",\n" +
                "  \"discount\": \"" + this.discount + "\",\n" +
                "  \"completed\": \"" + this.completed + "\"\n" +
                "}";
    }

    public Order(Integer clientId) {
        this.clientId = clientId;
        this.delivererId = 1;
        this.cookId = 1;
        this.deliveryTime = 60;
        this.discount = 0;
        this.completed = false;
    }

    public Order(Integer id, Order order) {
        this.id = id;
        this.delivererId = order.delivererId;
        this.cookId = order.cookId;
        this.deliveryTime = order.deliveryTime;
        this.discount = order.discount;
        this.completed = order.completed;
    }

}
