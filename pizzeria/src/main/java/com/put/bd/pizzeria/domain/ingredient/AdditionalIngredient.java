//package com.put.bd.pizzeria.domain.ingredient;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.put.bd.pizzeria.domain.OrderedDish;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.hibernate.annotations.OnDelete;
//import org.hibernate.annotations.OnDeleteAction;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "additional_ingredient")
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//public class AdditionalIngredient {
//
//    @Id
//    Integer id;
//
//    @ManyToOne
////    @JoinColumn(name = "ordered_dish_id", columnDefinition = "INT")
////    OrderedDish orderedDish;
//
////    @Column(name = "ingredient_id", columnDefinition = "INT")
////    Integer ingredientId;
//
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "ingredient_id", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
//    Ingredient ingredient;
//
//}
