import {Component, OnInit} from '@angular/core';
import {ApiService} from '../shared/api.service';
import {DishMenu, Ingredient} from '../menu/menu.component';
import {HttpErrorResponse} from '@angular/common/http';
import {Client} from '../clients/clients.component';

@Component({
  selector: 'app-make-order',
  templateUrl: './make-order.component.html',
  styleUrls: ['./make-order.component.css']
})
export class MakeOrderComponent implements OnInit {
  overallPrice = 0;
  client: Client = null;
  dishesInCart: DishMenu[] = [];
  order: Order = {
    client: null,
  completed: false,
  cookId: 1,
  delivererId: 1,
  deliveryTime: 999,
  discount: 0,
  id: null,
  orderedDishes: [],
  };
  dishesToOrder: OrderedDish[] = [];
  orderedDish: OrderedDish = {
    additionalIngredients: [],
    dishMenu: null,
  };
  constructor(private apiService: ApiService) {
  }

  ngOnInit() {
    this.getDishesFromCart();
  }

  getDishesFromCart() {
    this.overallPrice = 0;
    this.dishesInCart = JSON.parse(localStorage.getItem('cart'));
    let i;
    for (i = 0; i < this.dishesInCart.length; i++) {

      this.overallPrice +=  this.dishesInCart[i].price;
      this.orderedDish.additionalIngredients = [];
      this.orderedDish.dishMenu = this.dishesInCart[i];
      this.dishesToOrder.push( JSON.parse(JSON.stringify(this.orderedDish)));
    }
  }

  addIngredient(dish: DishMenu) {

  }

  removeFromCart(dish: DishMenu) {
    const index = this.dishesInCart.indexOf(dish);
    this.dishesInCart.splice(index, 1);
    localStorage.setItem('cart', JSON.stringify(this.dishesInCart));
    this.dishesToOrder = [];
    this.getDishesFromCart();
  }

  makeOrder() {
    this.apiService.getClientById(1).subscribe(
      res => {
        this.order.client = res; // JSON.parse(JSON.stringify(res));
        this.order.completed = false;
        this.order.cookId = 1;
        this.order.delivererId = 1;
        this.order.deliveryTime = 40;
        this.order.discount = 15;
        this.order.orderedDishes = this.dishesToOrder;
        this.apiService.makeOrder(4, this.order).subscribe(// id klienta
          result => {
            this.dishesInCart.splice(0);
            localStorage.setItem('cart', JSON.stringify(this.dishesInCart));
            this.getDishesFromCart();
          },
          (error: HttpErrorResponse) => {
            alert('ERROR: ' + JSON.stringify(error.error['message']));
          }
        );

      });

  }

}
export interface Order {
  client: Client;
  completed: boolean;
  cookId: number;
  delivererId: number;
  deliveryTime: number;
  discount: number;
  id: number;
  orderedDishes: OrderedDish[];
}
export interface OrderedDish {
  additionalIngredients: Ingredient[];
  dishMenu: DishMenu;

}
