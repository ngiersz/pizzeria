import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Client} from "../clients/clients.component";
import {ApiService} from "../shared/api.service";
import {Address} from "../addresses/addresses.component";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  currentClient: Client = null;
  currentClientAddress: Address = null;
  adId: number = null;
  constructor(private apiService: ApiService) { }


  ngOnInit() {
  }



}
