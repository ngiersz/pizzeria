package com.put.bd.pizzeria.domain.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "most_popular_pizza")
public class MostPopular {

    @Id
    @Column(name = "id", columnDefinition = "INTEGER")
    Integer id;

    @Column(name = "dish_menu_id", columnDefinition = "INT")
    Integer dishMenuId;

    @Column(name = "name", columnDefinition = "NVARCHAR(100)")
    String name;

    @Column(name = "quantity", columnDefinition = "INT")
    String quantity;

}
