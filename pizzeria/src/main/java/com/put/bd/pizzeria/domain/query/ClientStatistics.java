package com.put.bd.pizzeria.domain.query;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientStatistics {

    String firstName;
    String lastName;
    Float overallValue;
    Float valuePerOrder;
    Float maxOrderValue;
    Float minOrderValue;
    Integer numberOfOrders;
}
