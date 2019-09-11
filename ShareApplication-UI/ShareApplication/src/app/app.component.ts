import { Component } from '@angular/core';
import { Router } from '@angular/router';

// Defined this document with a component
@Component({
  selector: 'app-root',                 // refer this component as tag html
  templateUrl: './app.component.html',  // reference to document html (format component) for this component
  styleUrls: ['./app.component.css']    // reference to document css (style component) for this component
})

// export variables for used in others files of project
export class AppComponent {

  // Defined variables
  navbarOpen = false; // State of responsive button nav
  title = 'ShareApplication'; // Title in Navigator description

  // Constructor
  constructor(private router: Router) {}

  // Method responsive navbar toggle
  toggleNavbar() {
    this.navbarOpen = !this.navbarOpen;
  }

}
