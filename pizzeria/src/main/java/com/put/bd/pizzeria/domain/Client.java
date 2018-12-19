package com.put.bd.pizzeria.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

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
    @NotNull
    String firstName;

    @Column(name = "last_name", columnDefinition = "NVARCHAR(100)")
    @Size(max = 40)
    @NotNull
    String lastName;

    @Column(name = "email", columnDefinition = "NVARCHAR(100)")
    @Email(message = "Błędny format email")
    @NotNull
    String email;

    @Column(name = "phone_number", columnDefinition = "NVARCHAR(100)")
    @Size(min = 9, max = 12)
    @Pattern(regexp = "(\\+[0-9]{2})?[0-9]{9}", message = "Błędny numer telefonu. Poprawne formaty: +48123123123 lub 123123123.")
    @NotNull
    String phoneNumber;

    @ManyToOne(targetEntity = Address.class)
    @JoinColumn(name = "address_id")
    @NotNull
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
