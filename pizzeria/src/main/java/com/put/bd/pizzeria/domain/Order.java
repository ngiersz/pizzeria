package com.put.bd.pizzeria.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @NotNull
    Client client;

    @Column(name = "discount")
    Integer discount;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "order")
    private Set<OrderedDish> orderedDishes = new HashSet<>();

    public Order(Client client) {
        this.client = client ;
        this.discount = 0;
    }

}
