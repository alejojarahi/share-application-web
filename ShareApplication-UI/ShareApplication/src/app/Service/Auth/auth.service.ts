import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http'; // Import this module for request http

import { User } from 'src/app/Models/user.model'; // Import the interface of model User of used is propertys
import { JwtModel } from 'src/app/Models/Auth/jwt.model';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

// This class is a service, can be injectable in other components
@Injectable({
  providedIn: 'root'
})

// This service implement the methods for create new users and check in the login the data
export class AuthService {

  authUrl = 'http://localhost:8034/auth/';

  // Defined the modules to used in this class
  constructor(private http: HttpClient) { }

  // Method for autchenticate users in database with services REST using the url of service http://localhost:8034/auth/login
  userAuthentication(userAuth: User): Observable<JwtModel> {
    return this.http.post<JwtModel>(this.authUrl + 'login', userAuth, httpOptions);
  }

  // Method for create new users in database with services REST using the url of service http://localhost:8034/auth/register
  createNewUser(newUser: User): Observable<any> {
    return this.http.post<any>(this.authUrl + 'register', newUser, httpOptions);
  }

  existsDocument(document: number) {
    return this.http.get(this.authUrl + 'search?document=' + document);
  }

  existsEmail(email: string) {
    return this.http.get(this.authUrl + 'search?email=' + email);
  }
}
