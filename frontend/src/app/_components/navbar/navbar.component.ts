import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from '../../_services/authentication.service';
import {Router} from "@angular/router";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  loggedIn: boolean = false;

  constructor(
    private authService: AuthenticationService,
    private router: Router,
  ) {
  }

  ngOnInit(): void {
    this.loggedIn = this.authService.user != null;
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/']).then(() => window.location.reload());
  }
}
