import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormControl, FormGroup, ValidationErrors, Validators} from "@angular/forms";
import {AuthenticationService} from "../../_services/authentication.service";
import {Router} from "@angular/router";
import {AlertService} from "../../_services/alert.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup = new FormGroup({
    firstName: new FormControl('', [Validators.required, Validators.maxLength(50)]),
    lastName: new FormControl('', [Validators.required, Validators.maxLength(50)]),
    email: new FormControl('', [Validators.required, Validators.email, Validators.maxLength(100)]),
    password: new FormControl('', [Validators.required, Validators.minLength(6), Validators.maxLength(64)]),
    confirmPassword: new FormControl('', [Validators.required, Validators.minLength(6), Validators.maxLength(64)]),
  });
  loading = false;

  constructor(
    private authService: AuthenticationService,
    private router: Router,
    private alertService: AlertService,
  ) { }

  ngOnInit(): void {
    if (this.authService.user) {
      this.router.navigate(['/']).then(() => {
        this.alertService.warning('You are already signed in.');
      });
    }
  }

  onRegister() {
    if (!this.registerForm.valid) {
      return;
    }

    if (this.registerForm.value.password != this.registerForm.value.confirmPassword) {
      return;
    }

    this.loading = true;
    this.authService.register(
      this.registerForm.value.email,
      this.registerForm.value.password,
      this.registerForm.value.firstName,
      this.registerForm.value.lastName,
    ).subscribe({
      next: () => {
        this.router.navigate(['/']).then(() => {
          this.alertService.success("Registration successful!");
        });
      },
      error: err => {
        if (err.status === 400) {
          this.alertService.error('Email is already in use');
        } else {
          this.alertService.error(`Error: ${err.error.message}`);
        }
      },
    }).add(() => {
      this.loading = false;
    });
  }

  get email(): AbstractControl {
    return this.registerForm.get('email')!;
  }

  get password(): AbstractControl {
    return this.registerForm.get('password')!;
  }

  get confirmPassword(): AbstractControl {
    return this.registerForm.get('confirmPassword')!;
  }
}
