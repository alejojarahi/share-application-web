import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http'; // Import this module for request http

import { User } from '../Models/user.model'; // Import the interface of model User of used is propertys

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

// This class is a service, can be injectable in other components
@Injectable({
  providedIn: 'root'
})

export class UserService {

  // Url of user http request
  userUrl = 'http://localhost:8034/user/';

  // Defined the module to used in this class
  constructor(private http: HttpClient) { }

  getUsers() {
    return this.http.get<User[]>(this.userUrl + 'all');
  }

  getUserByDocument(document: number) {
    return this.http.get<User>(this.userUrl + document);
  }

  getUserByEmail(email: string) {
    return this.http.get<User>(this.userUrl + email);
  }
}
