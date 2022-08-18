import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BehaviorSubject, map, Observable} from "rxjs";
import {User} from "../models/user.model";
import {environment} from "../../environments/environment";

/**
 * Local storage key used to save and retrieve the currently authenticated user
 */
const AUTHENTICATED_USER_KEY = 'user';

/**
 * Service used to authenticate a user, and saving to local storage for later retrieval.
 */
@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  /**
   * The currently authenticated user
   * @private retrieved by calling user()
   */
  private _user$: BehaviorSubject<User | null>

  /**
   * Loads the current user from local storage if it exists
   */
  constructor(
    private _http: HttpClient,
  ) {
    this._user$ = new BehaviorSubject<User | null>(JSON.parse(<string>localStorage.getItem(AUTHENTICATED_USER_KEY)));
  }

  /**
   * Gets the currently authenticated user
   */
  public get user(): User | null {
    return this._user$.value;
  }

  /**
   * Makes an authentication request with the given email and password. On success returns the validated User. Otherwise,
   * throws an error.
   *
   * @param email user email
   * @param password user password
   * @returns an observable User on success, otherwise an error.
   */
  login(email: string, password: string): Observable<User> {
    return this._http.post<any>(
      `${environment.AUTHENTICATION_API_URL}/login`,
      {email, password},
    ).pipe(
      map(resp => this.saveUser(resp)!),
    );
  }

  /**
   * Registers a new user
   *
   * @param email user email
   * @param password user password
   * @param firstName user's first name
   * @param lastName user's last name
   */
  register(
    email: string,
    password: string,
    firstName: string,
    lastName: string,
  ): Observable<any> {
    return this._http.post(
      `${environment.AUTHENTICATION_API_URL}/register`,
      {email, password, firstName, lastName},
    );
  }

  /**
   * Logs out the user by deleting the user saved in memory and storage.
   */
  logout(): void {
    this.saveUser(null);
  }

  /**
   * Saves the user to local storage and outputs a new User to the user behavior subject.
   *
   * @param user user to save to storage and emit
   * @private called by login and logout
   */
  private saveUser(user: User | null): User | null {
    this._user$.next(user);
    localStorage.setItem(AUTHENTICATED_USER_KEY, JSON.stringify(user));
    return user;
  }
}
