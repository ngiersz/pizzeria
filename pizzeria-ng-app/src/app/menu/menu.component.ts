import { Component, OnInit } from '@angular/core';
import {ApiService} from "../shared/api.service";
import {HttpErrorResponse} from "@angular/common/http";
import {ClientModel} from "../clients/clients.component";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  dishes: DishModel[] = [];

  constructor(private apiService: ApiService) { }

  ngOnInit() {
    this.getAllDishes();

  }

  getAllDishes() {
    this.apiService.getAllDishes().subscribe(
      res => {
        this.dishes = JSON.parse(JSON.stringify(res));
      },
      (error: HttpErrorResponse) => {
        alert("error pobieranie dań");
      }
    );
  }
  model: DishModel[] = [{
    id: 0,
    lastModification: 0,
    price: 0,
    name: ''
  }];

  addToCart(dish: DishModel){
    localStorage.setItem("cart", JSON.stringify(this.model));
    let temp;
    temp = JSON.parse(localStorage.getItem("cart"));
    temp.push(dish);
    localStorage.setItem("cart", JSON.stringify(temp));
    alert("Danie zostało dodane do koszyka.");
  }

}

export interface DishModel {
  id: number,
  name: string,
  price: number,
  lastModification: number

}
