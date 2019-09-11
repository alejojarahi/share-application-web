import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http'; // Import this module for request http

import { User } from '../Models/user.model'; // Import the interface of model User of used is propertys

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})

export class UserService {

  UrlRegister = 'http://localhost:8034/user/adduser';
  UrlLogin = 'http://localhost:8034/user/';
  UrlUsers = 'http://localhost:8034/user/all';

  // Defined the module to used in this class
  constructor(private http: HttpClient) { }

  getUsers() {
    return this.http.get<User[]>(this.UrlUsers);
  }

  createUserHttp(newUser: User): Observable<User> {
    const body: User = {
      document: newUser.document,
      firstname: newUser.firstname,
      lastname: newUser.lastname,
      email: newUser.email,
      password: newUser.password
    }
    return this.http.post<User>(this.UrlRegister, body, httpOptions);
  }

  /*userAuthentication(email, password): Observable<User> {
    var headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(email.toLowerCase() + ':' + password) });
    return this.http.get(this.loginURL + data, { headers });
    return this.http.post<User>(this.UrlRegister, body, httpOptions);
  }*/
}