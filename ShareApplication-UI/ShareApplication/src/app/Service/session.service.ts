import { Injectable } from '@angular/core';
import { TokenService } from './Auth/token.service';

// Import the global constants use in this project
import { GlobalConstants } from '../globals';

// This class is a service, can be injectable in other components
@Injectable({
  providedIn: 'root'
})

// This class check the information of the session user
export class SessionService {

  constructor(private tokenService: TokenService) { }

  // Validate if the users are connected with the session
  public isLoginUser(): boolean {
    if (this.tokenService.getToken()) {
      return true;
    }
    return false;
  }

  // Get the list authorities of roles of the users
  public getListRoles(): string[] {
    if (this.isLoginUser() === true) {
      let roles = [];
      roles = this.tokenService.getAuthorities();
      return roles;
    }
    return null;
  }

  // Return the all roles that have assigne the users
  public getRoles(): string[] {
    let roles: string[];
    roles = [];

    // filter create a list of string date with roles that meet conditional
    // the position of the array is the rol position correspondent
    this.getListRoles().filter(rol => {
      if (rol === 'ROLE_USER') {
        roles[GlobalConstants.IS_ROL_USER] = 'user';
      }
      if (rol === 'ROLE_ADMIN') {
        roles[GlobalConstants.IS_ROL_ADMIN] = 'admin';
      }
      if (rol === 'ROLE_DRIVER') {
        roles[GlobalConstants.IS_ROL_DRIVER] = 'driver';
      }
    });
    return roles;
  }
}
