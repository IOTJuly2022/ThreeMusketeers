import {Component, Input, OnInit} from '@angular/core';
import {Alert} from "../../models/alert.model";
import {AlertService} from "../../_services/alert.service";
import {NavigationStart, Router} from "@angular/router";

@Component({
  selector: 'app-alert',
  templateUrl: './alert.component.html',
  styleUrls: ['./alert.component.scss']
})
export class AlertComponent implements OnInit {

  @Input() id: string = 'default';

  alerts: Alert[] = [];

  constructor(
    private alertService: AlertService,
    private router: Router,
  ) { }

  ngOnInit(): void {
    this.alertService.onAlert(this.id).subscribe(alert => {
      if (!alert.message) {
        this.alerts = [];
        return;
      }

      this.alerts.push(alert);
    });

    this.router.events.subscribe(event => {
      if (event instanceof NavigationStart) {
        this.alerts = this.alerts.filter(alert => alert.persist);
      }
    });
  }

  close(alert: Alert) {
    this.alerts = this.alerts.filter(x => x != alert);
  }
}
