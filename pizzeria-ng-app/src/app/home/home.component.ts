import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {ClientModel} from "../clients/clients.component";
import {ApiService} from "../shared/api.service";
import {AddressModel} from "../addresses/addresses.component";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  currentClient: ClientModel = null;
  currentClientAddress: AddressModel = null;
  adId: number = null;
  constructor(private apiService: ApiService) { }


  ngOnInit() {
    this.getCurrentClient(5);
  }

  getCurrentClient(id: number){

    this.apiService.getClientById(id).subscribe(
      res => {
        this.currentClient = JSON.parse(JSON.stringify(res));
        this.adId = this.currentClient.addressId;
        this.getCurrentClientAddress(this.currentClient.addressId);
      },
      (error: HttpErrorResponse) => {
        alert("error pobieranie klienta po ID");
      }
    );
  }

  getCurrentClientAddress(id: number){

    this.apiService.getAddressById(id).subscribe(
      res => {
        this.currentClientAddress = JSON.parse(JSON.stringify(res));
      },
      (error: HttpErrorResponse) => {
        alert("error pobieranie adresu klienta po ID");
      }
    );
  }

}
