import { Component, OnInit } from '@angular/core';
import {ApiService} from '../shared/api.service';
import {HttpErrorResponse} from '@angular/common/http';
import {Client} from '../clients/clients.component';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  dishes: DishMenu[] = [];

  constructor(private apiService: ApiService) { }
  dishesInCart: DishMenu[] = [];
  ngOnInit() {
    this.getAllDishes();
    if (JSON.parse(localStorage.getItem('cart')) == null) {
      localStorage.setItem('cart', JSON.stringify(this.dishesInCart));
    }

  }

  getAllDishes() {
    this.apiService.getAllDishes().subscribe(
      res => {
        this.dishes = JSON.parse(JSON.stringify(res));
      },
      (error: HttpErrorResponse) => {
        alert('error pobieranie dań');
      }
    );
  }


  addToCart(dish: DishMenu) {
    let temp;
    temp = JSON.parse(localStorage.getItem('cart'));
    temp.push(dish);
    localStorage.setItem('cart', JSON.stringify(temp));
    // alert('Danie zostało dodane do koszyka.');
  }

}
export interface Ingredient {
  id: number;
  name: string;
  price: number;
  quantity: number;
}
export interface DishMenu {
  id: number;
  name: string;
  price: number;
  basicIngredients: Ingredient[];

}
