import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { MDBBootstrapModule } from 'angular-bootstrap-md';

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
import { FooterComponent } from './footer/footer.component';
import { LogsComponent } from './logs/logs.component';
import { QueriesComponent } from './queries/queries.component';

const appRoutes :Routes = [
  {
    path:'home',
    component: HomeComponent
  },
  {
    path:'clients',
    component: ClientsComponent
  },
  {
    path:'logs',
    component: LogsComponent
  },
  {
    path:'queries',
    component: QueriesComponent
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
    component: HomeComponent
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
    FooterComponent,
    LogsComponent,
    QueriesComponent,
  ],
  imports: [  MDBBootstrapModule.forRoot(),
    BrowserModule,
    RouterModule.forRoot(appRoutes, {enableTracing:true}),
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
