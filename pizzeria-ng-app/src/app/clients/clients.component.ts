import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {Config} from "protractor";
import {catchError} from "rxjs/operators";
import {ApiService} from "../shared/api.service";

@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html',
  styleUrls: ['./clients.component.css']
})
export class ClientsComponent implements OnInit {
  model: ClientModel = {
    id: null,
    firstName: '',
    lastName: '',
    email: '',
    login:'',
    phoneNumber: '',
    addressId: null,
    amountOfOrders:null
  };

  clients: ClientModel[] = [];

  constructor(private apiService: ApiService) {

  }

  ngOnInit() {
    this.getAllClients();
  }

  addClient(): void {
    this.apiService.postClient(this.model).subscribe(
      res => {
        location.reload();
      },
      (error: HttpErrorResponse) => {
        alert(JSON.stringify(error.error["message"]));
      }
    );
  }

  getAllClients(){
    this.apiService.getAllClients().subscribe(
      res => {
        this.clients = JSON.parse(JSON.stringify(res));
      },
      (error: HttpErrorResponse) => {
        alert(error);
      }
    );
  }
}

export interface  ClientModel {
  id:number,
  firstName:string,
  lastName:string,
  email:string,
  phoneNumber:string,
  addressId:number,
  login:string,
  amountOfOrders:number
}
