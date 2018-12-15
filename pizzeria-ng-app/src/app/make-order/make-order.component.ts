import {Component, OnInit} from '@angular/core';
import {ApiService} from "../shared/api.service";
import {DishModel} from "../menu/menu.component";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-make-order',
  templateUrl: './make-order.component.html',
  styleUrls: ['./make-order.component.css']
})
export class MakeOrderComponent implements OnInit {
  overallPrice: number = 0;
  dishesInCart: DishModel[] = [];

  constructor(private apiService: ApiService) {
  }

  ngOnInit() {
    this.getDishesFromCart();
  }

  getDishesFromCart() {
    this.overallPrice = 0;
    this.dishesInCart = JSON.parse(localStorage.getItem("cart"));
    var i;
    for (i = 0; i < this.dishesInCart.length; i++)
      this.overallPrice += this.dishesInCart[i].price;
  }


  removeFromCart(dish: DishModel) {
    var index = this.dishesInCart.indexOf(dish);
    this.dishesInCart.splice(index, 1);
    localStorage.setItem("cart", JSON.stringify(this.dishesInCart));
    this.getDishesFromCart();
  }

  makeOrder() {
    alert(JSON.stringify(this.dishesInCart));

    this.apiService.makeOrder(5, this.dishesInCart).subscribe(// id klienta
      res => {
        this.dishesInCart.splice(0);
        localStorage.setItem("cart", JSON.stringify(this.dishesInCart));
        this.getDishesFromCart();
      },
      (error: HttpErrorResponse) => {
          alert("ERROR: " + JSON.stringify(error.error["message"]));
      }
    );
  }

}

