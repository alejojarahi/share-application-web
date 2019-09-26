import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

// Import the services use in this component
import { TokenService } from './Service/Auth/token.service';
import { SessionService } from './Service/session.service';

// Import the constants use in this project
import { GlobalConstants } from './globals';

// Defined this document as a component
@Component({
  selector: 'app-root',                 // refer this component as tag html
  templateUrl: './app.component.html',  // reference to document html (format component) for this component
  styleUrls: ['./app.component.css']    // reference to document css (style component) for this component
})

// export variables for used in others files of project
export class AppComponent implements OnInit {

  // Constants for use in the html page
  IS_ROL_USER  = GlobalConstants.IS_ROL_USER;
  IS_ROL_ADMIN = GlobalConstants.IS_ROL_ADMIN;

  // Defined variables
  navbarOpen = false; // State of responsive button nav
  title = 'ShareApplication'; // Title in Navigator description

  // Defined variables use for check the status of the session in this component
  isLogin = false;
  roles: string[];

  // Constructor that inject the TokenService and Router
  constructor(private sessionService: SessionService, private tokenService: TokenService, private router: Router) {}

  // Method responsive navbar toggle
  toggleNavbar() {
    this.navbarOpen = !this.navbarOpen;
  }

  ngOnInit() {
    // Check the token in the session and the status of de user and asigned privileges
    this.isLogin = this.sessionService.isLoginUser();
    if (this.isLogin) {
      this.roles = this.sessionService.getRoles();
    }
  }

  // This method is used for log out session
  logOut(): void {
    this.tokenService.logOut();
    this.isLogin = false;
    window.location.reload(); // Reload the navegation web application in the url current
  }
}
