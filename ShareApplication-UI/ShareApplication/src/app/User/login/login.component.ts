import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, AbstractControl } from '@angular/forms';
import { map } from 'rxjs/operators';

// Import services
import { AuthService } from 'src/app/Service/Auth/auth.service';
import { TokenService } from 'src/app/Service/Auth/token.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form: any = {};
  formLogin: FormGroup;

  isLogged = false;
  isLoginFail = false;
  roles: string[] = [];
  message = '';

  constructor(private authService: AuthService, private tokenService: TokenService, public formBuilder: FormBuilder) {
    this.buildForm();
  }

  ngOnInit() {
    // Check with the tokenService if there is a token in the session
    if (this.tokenService.getToken()) {
      this.isLogged = true;
      this.isLoginFail = false;
      this.roles = this.tokenService.getAuthorities();
    }
  }

  // Form validations for login
  buildForm() {
    this.formLogin = this.formBuilder.group({
      email:    ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]]
    });
  }

  // Method for the users sign in with application
  OnLogin(): void {

    this.authService.userAuthentication(this.formLogin.value).subscribe(data => {

      // Asgine the local data for token for configurate the session
      this.tokenService.setToken(data.token);
      this.tokenService.setUserName(data.email);
      this.tokenService.setAuthorities(data.authorities);

      // Data of the session login
      this.isLoginFail = false;
      this.roles = this.tokenService.getAuthorities();

      // Roload the web application in the url current
      window.location.reload();
    },
    (error: any) => { // Control errors in the login
      this.isLogged = false;
      this.isLoginFail = true;
      console.log(error.error.status);
      if (error.error.status === 401) {
        this.message = 'This email or password is incorrect';
      } else if (error.error.status === undefined) {
        this.message = error.error;
      } else {
        this.message = error.error.message;
      }
    });
  }

  // Validation for check if exists email in database
  validateEmail(control: AbstractControl) {
    return this.authService.existsEmail(control.value).pipe(
      map(result => result ? null : { emailNotExists: true })
    );
  }
}
