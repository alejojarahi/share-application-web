import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { UserService } from 'src/app/Service/user.service'; // Import the service user for used function for register users in database
import { User } from 'src/app/Models/user.model';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  newUser: User = new User();
  emailPattern = '^[a-z0-9._%+-]+@unal.edu.co$';

  constructor(private router: Router, private service: UserService) { }

  ngOnInit() {
  }

  saveUser() {
    this.service.createUser(this.newUser).subscribe(data => {
      alert('Usuario creado correctamente');
      this.router.navigate(['']);
    });
  }

}
