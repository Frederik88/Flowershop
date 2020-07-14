import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../_model/user.model';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private baseUrl: string;

  constructor(private httpClient: HttpClient, private router: Router) {
    this.baseUrl = 'http://localhost:8080/user';
  }

  login(user: User) {
    this.httpClient.post(this.baseUrl + '/login', {
      userName: user.username,
      password: user.password
    }).subscribe(isValid => {
      if (isValid) {
        sessionStorage.setItem(
          'token',
          btoa(user.username + ':' + user.password)
        );
        this.router.navigate(['']);
      } else {
        alert("Authentication failed.")
      }
    });
  }
}
