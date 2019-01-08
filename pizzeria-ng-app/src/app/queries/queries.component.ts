import { Component, OnInit } from '@angular/core';
import {ApiService} from '../shared/api.service';
import {HttpErrorResponse} from '@angular/common/http';

@Component({
  selector: 'app-queries',
  templateUrl: './queries.component.html',
  styleUrls: ['./queries.component.css']
})
export class QueriesComponent implements OnInit {
  clientValues: ClientValue[] = [];
  clientStatistics: ClientStatistic = {
    overallValue: 0,
  valuePerOrder: 0,
  maxOrderValue: 0,
  minOrderValue: 0,
  numberOfOrders: 0,
  firstName: '',
  lastName: '',
  };
  mostPopularPizza: MostPopularPizza = {
    dishMenuId: 0,
    name: '',
    quantity: 0,
  };
  isFound = false;
  show = false;
  price = 0;
  constructor(private apiService: ApiService) {}

  ngOnInit() {
    this.getAllClientValue();
    this.getMostPopularPizza();
  }
  getAllClientValue() {
    this.apiService.getAllClientValue().subscribe(
      res => {
        this.clientValues = JSON.parse(JSON.stringify(res));
      },
      (error: HttpErrorResponse) => {
        // alert(error);
      }
    );
  }

  getClientStatisticsById(idClient) {
    this.apiService.getClientStatisticsById(idClient).subscribe(
      res => {
        this.clientStatistics = JSON.parse(JSON.stringify(res));
        this.isFound = true;
      },
      (error: HttpErrorResponse) => {
        // alert(error);
      }
    );
  }
  getMostPopularPizza() {
    this.apiService.getMostPopularPizza().subscribe(
      res => {
        this.mostPopularPizza = JSON.parse(JSON.stringify(res));
      },
      (error: HttpErrorResponse) => {
        // alert(error);
      }
    );
  }
  getOrderPriceById(idOrder) {
    this.apiService.getOrderPriceById(idOrder).subscribe(
      res => {
        this.price = res;
        this.show = true;
      },
      (error: HttpErrorResponse) => {
        // alert(error);
      }
    );
  }
}

export interface ClientValue {
  clientId: number;
  firstName: string;
  lastName: string;
  price: number;
}

export interface ClientStatistic {
  overallValue: number;
  valuePerOrder: number;
  maxOrderValue: number;
  minOrderValue: number;
  numberOfOrders: number;
  firstName: string;
  lastName: string;
}

export interface MostPopularPizza {
  dishMenuId: number;
  name: string;
  quantity: number;
}

