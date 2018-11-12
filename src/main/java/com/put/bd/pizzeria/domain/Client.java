package com.put.bd.pizzeria.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "email")
    String email;

    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "address_id")
    int addressId;

    @Column(name = "login")
    String login;

    public Client(Long id, Client client) {
        this.id = id;
        this.firstName = client.firstName;
        this.lastName = client.lastName;
        this.email = client.email;
        this.phoneNumber = client.phoneNumber;
        this.addressId = client.addressId;
        this.login = client.login;
    }

    @Override
    public String toString() {
        return login + "; " + firstName + " " + lastName + " (" + email + "; " + phoneNumber + "; address_id = " + addressId;
    }
}
