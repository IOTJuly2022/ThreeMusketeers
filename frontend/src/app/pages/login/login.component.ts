import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthenticationService} from "../../_services/authentication.service";
import {Router} from "@angular/router";

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
  ) { }

  ngOnInit(): void {
    if (this.authenticationService.user) {
      this.router.navigate(['/']).then(() => {
        console.log('already authenticated');
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
      // Alert that failsed
      console.log('invalid form');
      return false;
    }

    this.loading = true;

    // Login Logic
    this.authenticationService.login(this.loginForm.value.email, this.loginForm.value.password).subscribe({
      next: () => {
        this.router.navigate(['/']).then(() => {
          console.log('success');
        })
      },
      error: (err) => {
        console.log('error', err);
      },
    });

    this.loading = false;
    return true;
  }

}