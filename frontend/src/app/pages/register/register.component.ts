import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, ValidationErrors, Validators} from "@angular/forms";
import {AuthenticationService} from "../../_services/authentication.service";
import {Router} from "@angular/router";

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
  ) { }

  ngOnInit(): void {
    if (this.authService.user) {
      this.router.navigate(['/']).then(() => {
        console.log('already authenticated');
      });
    }
  }

  onRegister() {
    if (!this.registerForm.valid) {
      console.log('form invalid');
      return;
    }

    if (this.registerForm.value.password != this.registerForm.value.confirmPassword) {
      console.log('passwords do not match');
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
          console.log('registered');
        });
      },
      error: err => {
        console.log('Error:', err);
      },
    }).add(() => {
      this.loading = false;
    });
  }
}
