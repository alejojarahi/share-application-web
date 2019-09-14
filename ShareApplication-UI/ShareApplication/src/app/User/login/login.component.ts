import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/Service/Auth/auth.service';
import { TokenService } from 'src/app/Service/Auth/token.service';
import { User } from 'src/app/Models/user.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form: any = {};
  user: User;
  isLogged = false;
  isLoginFail = false;
  roles: string[] = [];
  errorMsg = '';

  constructor(private authService: AuthService, private tokenService: TokenService, private router: Router) { }

  ngOnInit() {
    // Check with the tokenService if there is a token in the session
    if (this.tokenService.getToken()) {
      this.isLogged = true;
      this.isLoginFail = false;
      this.roles = this.tokenService.getAuthorities();
    }
  }

  // Method for the users sign in with application
  OnLogin(email: string, password: string): void {
    this.user = new User();
    this.user.email = email;
    this.user.password = password;

    this.authService.userAuthentication(this.user).subscribe(data => {

      // Asgine the local data for token for configurate the session
      this.tokenService.setToken(data.token);
      this.tokenService.setUserName(data.email);
      this.tokenService.setAuthorities(data.authorities);

      // Data of the session login
      this.isLogged = true;
      this.isLoginFail = false;
      this.roles = this.tokenService.getAuthorities();

      // Roload the web application in the url current
      window.location.reload();

      // Redirect to page home
      //this.router.navigate(['/home']);
    },

    (error: any) => { // Control errors in the login
      this.isLogged = false;
      this.isLoginFail = true;
      this.errorMsg = error.error.message;
    });
  }
}
