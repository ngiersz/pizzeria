package com.put.bd.pizzeria.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "first_name")
    @Size(max = 20)
    String firstName;

    @Column(name = "last_name")
    @Size(max = 40)
    String lastName;

    @Column(name = "email")
    @NotEmpty
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
