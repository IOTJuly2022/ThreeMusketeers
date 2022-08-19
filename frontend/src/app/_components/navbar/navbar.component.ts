import { Component, OnInit } from '@angular/core';
import { AuthenticationService} from '../../_services/authentication.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  constructor(private authService : AuthenticationService){}
  loggedIn : boolean = false;
  ngOnInit(): void {
    this.loggedIn = this.authService.user != null;
  }

}
