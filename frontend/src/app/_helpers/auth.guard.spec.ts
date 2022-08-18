import {TestBed} from '@angular/core/testing';

import {AuthGuard} from './auth.guard';
import {RouterTestingModule} from "@angular/router/testing";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {ActivatedRouteSnapshot, Router, RouterStateSnapshot} from "@angular/router";
import {AuthenticationService} from "../_services/authentication.service";

describe('AuthGuard', () => {
  let guard: AuthGuard;
  let router = {
    navigate: jasmine.createSpy('navigate'),
  };
  let authenticationService: AuthenticationService;
  let routerStateSnapshot = jasmine.createSpyObj<RouterStateSnapshot>('RouterStateSnapshot', [], {url: 'https://test.com/'});
  let activatedRouteSnapshot = jasmine.createSpyObj<ActivatedRouteSnapshot>(
    'ActivatedRouterSnapshot',
    [],
    {data: {['role']: 'ADMIN'}},
  );

  const promise = Promise.resolve();

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        HttpClientTestingModule,
      ],
      providers: [
        {provide: Router, useValue: router},
        {provide: RouterStateSnapshot, useValue: routerStateSnapshot},
        {provide: ActivatedRouteSnapshot, useValue: activatedRouteSnapshot},
      ],
    }).compileComponents();
    guard = TestBed.inject(AuthGuard);
    authenticationService = TestBed.inject(AuthenticationService);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });


  it('should fail with no authenticated user', () => {
    spyOnProperty(authenticationService, 'user').and.returnValue(undefined);
    expect(guard.canActivate(activatedRouteSnapshot, routerStateSnapshot)).toBeFalsy();
    expect(router.navigate).toHaveBeenCalledWith(['/login'], {queryParams: {returnUrl: routerStateSnapshot.url}});
  });


  it('succeed if they logged in', () => {
    spyOnProperty(authenticationService, 'user').and.returnValue({
      email: "",
      firstName: "",
      id: 0,
      lastName: "",
      token: "",
    });

    router.navigate.and.returnValue(promise);
    expect(guard.canActivate(activatedRouteSnapshot, routerStateSnapshot)).toBeTruthy();
  });
});
