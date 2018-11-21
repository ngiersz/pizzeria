package com.put.bd.pizzeria.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "\"order\"")
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
}
