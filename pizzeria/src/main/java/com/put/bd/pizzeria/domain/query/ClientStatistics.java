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
    Integer overallValue;
    Integer valuePerOrder;
    Integer maxOrderVaue;
    Integer minOrderValue;
}
