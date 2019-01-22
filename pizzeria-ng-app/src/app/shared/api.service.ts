import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Client} from '../clients/clients.component';
import {Address} from '../addresses/addresses.component';
import {DishMenu} from '../menu/menu.component';
import {Order} from '../make-order/make-order.component';
import {Log} from '../logs/logs.component';
import {ClientStatistic, ClientValue, MostPopularPizza} from '../queries/queries.component';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private  BASE_URL = 'http://192.168.43.143:8080';
  private  ALL_CLIENTS_URL = `${this.BASE_URL}\\clients`;
  private  SEND_CLIENT_URL = `${this.BASE_URL}\\clients`;
  private  GET_CLIENT_URL = `${this.BASE_URL}\\clients\\`;
  private  GET_ADDRESS_URL = `${this.BASE_URL}\\addresses\\`;
  private  ALL_DISHES_URL = `${this.BASE_URL}\\dishes`;
  private  MAKE_ORDER_URL = `${this.BASE_URL}\\orders\\`;
  private  ALL_LOGS_URL = `${this.BASE_URL}\\logs`;
  private  CLIENT_VALUE_URL = `${this.BASE_URL}\\query\\clientValue`;
  private  CLIENT_STATISTICS_URL = `${this.BASE_URL}\\query\\clientStatistics\\`;
  private  GET_MOST_POPULAR_PIZZA_URL = `${this.BASE_URL}\\query\\mostPopularPizza`;
  private  GET_ORDER_PRICE = `${this.BASE_URL}\\query\\orderPrice\\`;


  constructor(private http: HttpClient) { }

  getAllClients(): Observable<Client[]> {
    return this.http.get<Client[]>(this.ALL_CLIENTS_URL);
  }

  postClient(client: Client): Observable<any> {
    return this.http.post(this.SEND_CLIENT_URL, client);
  }

  getClientById(id: number): Observable<Client> {
    return this.http.get<Client>(this.GET_CLIENT_URL + id);
  }

  getOrderPriceById(id: number): Observable<any> {
    return this.http.get(this.GET_ORDER_PRICE + id);
  }

  getMostPopularPizza(): Observable<MostPopularPizza> {
    return this.http.get<MostPopularPizza>(this.GET_MOST_POPULAR_PIZZA_URL);
  }


  getClientStatisticsById(id: number): Observable<ClientStatistic> {
    return this.http.get<ClientStatistic>(this.CLIENT_STATISTICS_URL + id);
  }

  getAddressById(id: number): Observable<Address> {
    return this.http.get<Address>(this.GET_ADDRESS_URL + id);
  }

  getAllDishes(): Observable<DishMenu[]> {
    return this.http.get<DishMenu[]>(this.ALL_DISHES_URL);
  }

  getAllLogs(): Observable<Log[]> {
    return this.http.get<Log[]>(this.ALL_LOGS_URL);
  }

  getAllClientValue(): Observable<ClientValue[]> {
    return this.http.get<ClientValue[]>(this.CLIENT_VALUE_URL);
  }

  makeOrder(id: number, order: Order): Observable<any> {
    return this.http.post(this.MAKE_ORDER_URL + id, order);
  }
}
