import {Injectable} from '@angular/core';
import {filter, Observable, Subject} from "rxjs";
import {Alert} from "../models/alert.model";

@Injectable({
  providedIn: 'root'
})
export class AlertService {
  private _alert$ = new Subject<Alert>();

  constructor() {
  }

  onAlert(id?: string): Observable<Alert> {
    return this._alert$.asObservable().pipe(filter(a => a && a.id == id));
  }

  alert(alert: Alert) {
    this._alert$.next(alert);
  }

  clear(id?: string) {
    this._alert$.next(new Alert({id}))
  }
}
