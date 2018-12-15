import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { NavigationComponent } from './navigation/navigation.component';
import { ClientsComponent } from './clients/clients.component';
import { AddressesComponent } from './addresses/addresses.component';
import { NotFoundComponent } from './not-found/not-found.component';
import {Router, RouterModule, Routes} from "@angular/router";
import {FormsModule} from "@angular/forms";
import  {HttpClientModule} from "@angular/common/http";
import { HomeComponent } from './home/home.component';
import { MakeOrderComponent } from './make-order/make-order.component';
import { MenuComponent } from './menu/menu.component';

const appRoutes :Routes = [
  {
    path:'',
    component: HomeComponent
  },
  {
    path:'clients',
    component: ClientsComponent
  },
  {
    path:'addresses',
    component: AddressesComponent
  },
  {
    path:'menu',
    component: MenuComponent,
  },
  {
    path:'makeOrder',
    component: MakeOrderComponent,
  },
  {
    path:'',
    component: ClientsComponent
  },
  {
    path:'**',
    component:NotFoundComponent
  }
];

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    ClientsComponent,
    AddressesComponent,
    NotFoundComponent,
    HomeComponent,
    MakeOrderComponent,
    MenuComponent,
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(appRoutes, {enableTracing:true}),
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
