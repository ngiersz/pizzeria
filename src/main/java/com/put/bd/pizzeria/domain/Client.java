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
//    @NotEmpty
    String email;

    @Column(name = "phone_number", columnDefinition = "NVARCHAR(100)")
    String phoneNumber;

    @Column(name = "address_id")
    int addressId;

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
        this.addressId = client.addressId;
        this.login = client.login;
        this.amountOfOrders = client.amountOfOrders;
    }

//    @Override
//    public String toString()
//    {
//        return "{\n" +
//                "\"id\":\"" + id + "\",\n" +
//                "\"first_name\":\"" + firstName + "\",\n" +
//                "\"last_name\":\"" + lastName + "\",\n" +
//                "\"login\":\"" + login + "\",\n" +
//                "\"email\":\"" + email + "\",\n" +
//                "\"phone_number\":\"" + phoneNumber + "\",\n" +
//                "\"address_id\":\"" + addressId + "\"\n}";
//    }
}
