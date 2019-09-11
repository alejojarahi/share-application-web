import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/Models/user.model';
import { UserService } from 'src/app/Service/user.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  // Dependency Injection
  constructor(private service: UserService, private router: Router) { }

  // List users in database
  users: User[] = [];

  ngOnInit() {
    this.service.getUsers().subscribe(data => {
        this.users = data;
        console.log(data);  // print in consola the datas
      });

  }

}
