import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';

// Import the service use in this class
import { TokenService } from './Auth/token.service';

// This class is a service, can be injectable in other components
@Injectable({
  providedIn: 'root'
})

// This service check if exist a session with a token, username (email) and privileges in the navigator
// The service intercept all the requests http and add the token to the request and send it
export class InterceptorService implements HttpInterceptor {

  constructor(private tokenService: TokenService) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    // get format of the request
    let authRequest = request;

    // get the token of local user
    const token = this.tokenService.getToken();

    // check if the user have a token
    if (token != null) {

      // create new request with concatenate in the format of the request, the format Authorization
      authRequest = request.clone({
        headers: request.headers.set('Authorization', 'Bearer ' + token)
      });
    }
    return next.handle(authRequest);
  }
}
