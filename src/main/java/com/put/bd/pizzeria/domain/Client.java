package com.put.bd.pizzeria.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NonNull
    Long id;// = new Long(10L);

    @Column(name = "first_name")
    @NonNull
    String firstName;

    @Column(name = "last_name")
    @NonNull
    String lastName;

    @Column(name = "email")
    @NonNull
    String email;

    @Column(name = "phone_number")
    @NonNull
    String phoneNumber;

    int address_id = 1;

    public Client(Long id, Client client) {
        this.id = id;
        this.firstName = client.firstName;
        this.lastName = client.lastName;
        this.email = client.email;
        this.phoneNumber = client.phoneNumber;
    }

//    Address address;

}
