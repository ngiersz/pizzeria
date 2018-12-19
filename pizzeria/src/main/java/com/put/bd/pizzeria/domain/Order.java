package com.put.bd.pizzeria.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToOne(targetEntity = Client.class)
    @JoinColumn(name = "client_id")
    Client client;

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

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "order")
    private Set<OrderedDish> orderedDishes = new HashSet<>();

    public Order(Integer clientId) {
//        this.client = client;
//        this.delivererId = 1;
//        this.cookId = 1;
//        this.deliveryTime = 60;
//        this.discount = 0;
//        this.completed = false;
        throw new NotImplementedException();
    }

}
