import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ClientModel} from "../clients/clients.component";
import {AddressModel} from "../addresses/addresses.component";
import {DishModel} from "../menu/menu.component";

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private  BASE_URL = "http://192.168.1.65:8080";
  private  ALL_CLIENTS_URL = `${this.BASE_URL}\\clients`;
  private  SEND_CLIENT_URL = `${this.BASE_URL}\\clients`;
  private  GET_CLIENT_URL = `${this.BASE_URL}\\clients\\`;
  private  GET_ADDRESS_URL = `${this.BASE_URL}\\addresses\\`;
  private  ALL_DISHES_URL = `${this.BASE_URL}\\dishes`;
  private  MAKE_ORDER_URL = `${this.BASE_URL}\\orders\\`;


  constructor(private http: HttpClient) { }

  getAllClients() : Observable<ClientModel[]>{
    return this.http.get<ClientModel[]>(this.ALL_CLIENTS_URL);
  }

  postClient(client: ClientModel) : Observable<any>{
    return this.http.post(this.SEND_CLIENT_URL, client);
  }

  getClientById(id: number) : Observable<ClientModel> {
    return this.http.get<ClientModel>(this.GET_CLIENT_URL + id);
  }

  getAddressById(id: number) : Observable<AddressModel> {
    return this.http.get<AddressModel>(this.GET_ADDRESS_URL + id);
  }

  getAllDishes() : Observable<DishModel[]>{
    return this.http.get<DishModel[]>(this.ALL_DISHES_URL);
  }

  makeOrder(id: number, dishesInCart: DishModel[])  : Observable<any>{
    return this.http.post(this.MAKE_ORDER_URL + id, dishesInCart);
  }
}
