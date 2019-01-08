package com.put.bd.pizzeria.domain.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "client_value")
public class ClientValue {

    @Id
    @Column(name = "id", columnDefinition = "BIGINT")
    BigInteger id;

    @Column(name = "client_id", columnDefinition = "INT")
    String clientId;

    @Column(name = "first_name", columnDefinition = "NVARCHAR(100)")
    String firstName;

    @Column(name = "last_name", columnDefinition = "NVARCHAR(100)")
    String lastName;

    @Column(name = "price", columnDefinition = "MONEY")
    BigDecimal price;
}
