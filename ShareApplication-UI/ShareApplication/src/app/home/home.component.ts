import { Component, OnInit } from '@angular/core';
import { TokenService } from '../Service/Auth/token.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  info: any = {}; // Save the data token, username of user (email) and privileges

  constructor(private tokenService: TokenService) { }

  ngOnInit() {
    this.info = { // Check information login token for the users
      token: this.tokenService.getToken(),
      email: this.tokenService.getUserName(),
      authorities: this.tokenService.getAuthorities()
    };
  }
}
