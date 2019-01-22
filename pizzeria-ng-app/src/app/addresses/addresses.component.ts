import { Component, OnInit } from '@angular/core';
import {ApiService} from '../shared/api.service';
import {HttpErrorResponse} from '@angular/common/http';

@Component({
  selector: 'app-addresses',
  templateUrl: './addresses.component.html',
  styleUrls: ['./addresses.component.css']
})
export class AddressesComponent implements OnInit {
  constructor(private apiService: ApiService) { }
model: Address = {
  id: null,
  street: '',
  apartmentNumber: null,
  city: '',
  district: ''
};

  address: Address = null;

  ngOnInit() {
  }

  getAddress(id: number) {
  this.apiService.getAddressById(id).subscribe(
    res => {
      this.address = JSON.parse(JSON.stringify(res));
      alert(this.address.city);
    },
    (error: HttpErrorResponse) => {
      alert('error pobieranie adresu');
    }
  );
  }

}
export interface  Address {
  id: number;
  street: string;
  apartmentNumber: number;
  city: string;
  district: string;
}
