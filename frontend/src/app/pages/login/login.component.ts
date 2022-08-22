import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthenticationService} from "../../_services/authentication.service";
import {Router} from "@angular/router";
import {AlertService} from "../../_services/alert.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loading: boolean = false;
  loginForm: FormGroup = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', Validators.required),
  });

  constructor(
    private authenticationService: AuthenticationService,
    private router: Router,
    private alertService: AlertService,
  ) { }

  ngOnInit(): void {
    if (this.authenticationService.user) {
      this.router.navigate(['/']).then(() => {
        this.alertService.warning('You are already signed in.');
      });
    }
  }

  /**
   * Attempt to sign in the user with the given credentials.
   *
   * @return false if the login form has invalid data, otherwise true
   */
  onLogin(): boolean {
    if (!this.loginForm.valid) {
      return false;
    }

    this.loading = true;

    // Login Logic
    this.authenticationService.login(this.loginForm.value.email, this.loginForm.value.password).subscribe({
      next: () => {
        this.router.navigate(['/']).then(() => {
          window.location.reload();
          this.alertService.success("Successfully logged in!");
        })
      },
      error: (err) => {
        if (err.status === 401) {
          this.alertService.error('Invalid email and password combination');
        } else {
          this.alertService.error(`Error: ${err.error.message}`);
        }
      },
    });

    this.loading = false;
    return true;
  }
}
