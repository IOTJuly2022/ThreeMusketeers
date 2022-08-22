import { Injectable } from '@angular/core';
import {AuthenticationService} from "../_services/authentication.service";
import {HttpClient} from "@angular/common/http";
import {BehaviorSubject, map, Observable, of} from "rxjs";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private authService : AuthenticationService,
              private httpClient : HttpClient) { }

  getAllOrdersForCurrentUsers(){
    let user = this.authService.user;
    if(!user){
      return of([]);
    }
    return this.httpClient.get(`${environment.AUTHENTICATION_API_URL}/users/${user.id}/cart`);
  }


}
