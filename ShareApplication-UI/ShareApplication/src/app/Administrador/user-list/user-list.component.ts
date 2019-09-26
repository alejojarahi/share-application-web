import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';

// Import the models use in this component
import { User } from 'src/app/Models/user.model';

// Import the services use in this component
import { UserService } from 'src/app/Service/user.service';
import { SessionService } from 'src/app/Service/session.service';

// Import the global constants use in this project
import { GlobalConstants } from 'src/app/globals';

// This class is a component
@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})

export class UserListComponent implements OnInit {

  // Constants for use in the html page
  IS_ROL_USER  = GlobalConstants.IS_ROL_USER;
  IS_ROL_ADMIN = GlobalConstants.IS_ROL_ADMIN;

  // Define variables use for define the status of session and roles
  isLogin = false;
  @Input() roles: string[];

  // Dependency Injection
  constructor(private sessionService: SessionService, private userService: UserService, private router: Router) { this.loading = true; }

  // List users in database
  users: User[] = [];

  loading = true;

  // Function for constant check the status in this component
  ngOnInit() {

    // Check the token in the session and the status of de user and asigned privileges
    this.isLogin = this.sessionService.isLoginUser();
    if (this.isLogin) {
      this.roles = this.sessionService.getRoles();
    }

    // This load the information the all users in the array for print in page
    this.userService.getUsers().subscribe(data => {
      this.users = data;
      this.loading = false;
    });
  }
}
