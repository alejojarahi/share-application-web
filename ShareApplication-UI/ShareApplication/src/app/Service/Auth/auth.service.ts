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

  userAuthentication(userAuth: User): Observable<JwtModel> {
    console.log(userAuth);
    return this.http.post<JwtModel>(this.authUrl + 'login', userAuth, httpOptions);
  }

  // Method for create new users in database with services REST using the url of service http://localhost:8034/auth/register
  createUserHttp(newUser: User): Observable<User> {
    const user: User = {
      document: newUser.document,
      firstname: newUser.firstname,
      lastname: newUser.lastname,
      email: newUser.email,
      password: newUser.password,
      roles: ['ROLE_USER'] // This is default role, the value is irrelevant, the backend generate the rol for defect
    }
    return this.http.post<User>(this.authUrl + 'register', user, httpOptions);
  }
}
