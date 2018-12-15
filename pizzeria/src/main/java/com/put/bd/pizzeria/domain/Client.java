package com.put.bd.pizzeria.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "first_name", columnDefinition = "NVARCHAR(100)")
    @Size(max = 20)
    String firstName;

    @Column(name = "last_name", columnDefinition = "NVARCHAR(100)")
    @Size(max = 40)
    String lastName;

    @Column(name = "email", columnDefinition = "NVARCHAR(100)")
    String email;

    @Column(name = "phone_number", columnDefinition = "NVARCHAR(100)")
    String phoneNumber;

    @ManyToOne(targetEntity = Address.class)
    @JoinColumn(name = "address_id")
    Address address;

    @Column(name = "login", columnDefinition = "NVARCHAR(100)")
    String login;

    @Column(name = "amount_of_orders")
    Integer amountOfOrders;

    public Client(Integer id, Client client) {
        this.id = id;
        this.firstName = client.firstName;
        this.lastName = client.lastName;
        this.email = client.email;
        this.phoneNumber = client.phoneNumber;
        this.address = client.address;
        this.login = client.login;
        this.amountOfOrders = client.amountOfOrders;
    }

    @Override
    public String toString()
    {
        return id + ": " + firstName + " " + lastName + "; amountOfOrders: " + amountOfOrders;
    }
}
