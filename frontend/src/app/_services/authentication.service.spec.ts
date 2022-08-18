import { TestBed } from '@angular/core/testing';

import { AuthenticationService } from './authentication.service';
import {HttpClient} from "@angular/common/http";
import {User} from "../models/user.model";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {dummyUser} from "../_helpers/dummy.data";
import {HttpClientTestingModule} from "@angular/common/http/testing";

describe('AuthenticationService', () => {
  let service: AuthenticationService;
  let http: HttpClient;
  let user: User;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    }).compileComponents();
    service = TestBed.inject(AuthenticationService);
    http = TestBed.inject(HttpClient);
    user = dummyUser;
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });


  it('should allow logout', () => {
    const spy = spyOn(http, 'post').and.returnValue(new Observable((subscriber) => {
      subscriber.next({data: user});
      subscriber.complete();
    }));

    service.login(user.email, 'password');
    expect(service.user).toBeDefined();
    service.logout();
    expect(service.user).toBeNull();
  });

  it('should allow login', () => {
    const email = 'asdf';
    const password = 'jkl';
    const spy = spyOn(http, 'post').and.returnValue(new Observable((subscriber) => {
      subscriber.next(user);
      subscriber.complete();
    }));

    service.login(email,password).subscribe((returnedUser) => {
      expect(user).toEqual(returnedUser);
      expect(service.user).toEqual(user);
    });

    expect(spy).toHaveBeenCalledTimes(1);
    expect(spy).toHaveBeenCalledOnceWith(`${environment.AUTHENTICATION_API_URL}/login`, {email, password});
  });

  it('should allow registration', () => {
    const resp = 'resp';
    const spy = spyOn(http, 'post').and.returnValue(new Observable((subscriber) => {
      subscriber.next(resp);
      subscriber.complete();
    }));

    let password = 'password';
    service.register(user.email, password, user.firstName, user.lastName).subscribe((returnedResp) => {
      expect(returnedResp).toEqual(resp);
      expect(spy).toHaveBeenCalledOnceWith(`${environment.AUTHENTICATION_API_URL}/register`, {email: user.email, password, firstName: user.firstName, lastName: user.lastName});
      expect(spy).toHaveBeenCalledTimes(1);
    });
  });
});
