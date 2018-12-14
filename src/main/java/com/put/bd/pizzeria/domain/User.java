package com.put.bd.pizzeria.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="login")
@ToString
@Builder
@Entity
@Table(name = "USERS")
public class User {

    public enum Role {
        USER, ADMIN
    }

    @Id
    private String login;

    @Setter
    private String passwordHash;

    @Enumerated
    @NotNull
    private Role role;

}
