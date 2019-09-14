import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { TokenService } from '../Auth/token.service';

// This class is a service, can be injectable in other components
@Injectable({
  providedIn: 'root'
})

// This class check if the user when enter to a route, if have a token and role assigned
export class GuardService implements CanActivate {

  realRol: string;

  constructor(private tokenService: TokenService, private router: Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {

    const expectedRol = route.data.expectedRol;
    const roles = this.tokenService.getAuthorities();

    this.realRol = 'user';

    roles.forEach(rol => {
      if (rol === 'ROLE_ADMIN') {
        this.realRol = 'admin';
      }
    });

    // If the client haven´t token or role assigned, this he is redirect to home page
    if (this.tokenService.getToken() == null || expectedRol.indexOf(this.realRol) === -1) {
      this.router.navigate(['']);
      return false;
    }
    return true;
  }
}
