import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/Service/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private router: Router, private service: UserService) { }

  ngOnInit() {
  }

  /*loginUser(email, password) {

    this.service.userAuthentication(email, password).subscribe((data : any)=>{

    this.service.createUser(this.newUser).subscribe(data => {
      alert('Usuario creado correctamente');
      this.router.navigate(['']);
    });
  }*/

}
