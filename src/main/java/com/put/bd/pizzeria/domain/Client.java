package com.put.bd.pizzeria.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "Firstname")
    String firstName;

    @Column(name = "Lastname")
    String lastName;

    @Column(name = "Email")
    String email;

    @Column(name = "PhoneNumber")
//    @Max(12)
    String phoneNumber;

    public Client(Long id, Client client) {
        this.id = id;
        this.firstName = client.firstName;
        this.lastName = client.lastName;
        this.email = client.email;
//        this.phoneNumber = client.phoneNumber;
    }

//    Address address;

}
