import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {Config} from 'protractor';
import {catchError} from 'rxjs/operators';
import {ApiService} from '../shared/api.service';
import {Address} from '../addresses/addresses.component';

@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html',
  styleUrls: ['./clients.component.css']
})
export class ClientsComponent implements OnInit {
  model: Client = {
    address: null,
    id: null,
    firstName: '',
    lastName: '',
    email: '',
    login: '',
    phoneNumber: '',
    addressId: null,
    amountOfOrders: null
  };

  clients: Client[] = [];

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
        alert(JSON.stringify(error.error['message']));
      }
    );
  }

  getAllClients() {
    this.apiService.getAllClients().subscribe(
      res => {
        console.log(res);
        this.clients = res; // JSON.parse(JSON.stringify(res));

      },
      (error: HttpErrorResponse) => {
        alert(error);
      }
    );
  }
}

export interface  Client {
  address: Address;
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  phoneNumber: string;
  addressId: number;
  login: string;
  amountOfOrders: number;
}
