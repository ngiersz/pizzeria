package com.put.bd.pizzeria.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "client")
@JsonApiResource(type = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonApiId
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

    
}
