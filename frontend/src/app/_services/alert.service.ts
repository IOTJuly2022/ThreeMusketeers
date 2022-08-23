import {Injectable} from '@angular/core';
import {filter, Observable, Subject} from "rxjs";
import {Alert, AlertType} from "../models/alert.model";

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

  success(message: string, data: any = null, id: string = 'default') {
    this.alert(new Alert({message, id, type: AlertType.Success, ...data}));
  }

  error(message: string, data: any = null, id: string = 'default') {
    this.alert(new Alert({message, id, type: AlertType.Error}));
  }

  warning(message: string, data: any = null, id: string = 'default') {
    this.alert(new Alert({message, id, type: AlertType.Warning}));
  }

  info(message: string, data: any = null, id: string = 'default') {
    this.alert(new Alert({message, id, type: AlertType.Info}));
  }

  private alert(alert: Alert) {
    this._alert$.next(alert);
  }

  clear(id?: string) {
    this._alert$.next(new Alert({id}))
  }
}
