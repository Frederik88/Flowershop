import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { JwtHelperService } from '@auth0/angular-jwt';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private baseUrl: string;
  jwtHelper = new JwtHelperService();

  constructor(private httpClient: HttpClient, private router: Router) {
    this.baseUrl = 'http://localhost:8080/';
  }

  loggedIn() {
    const token = localStorage.getItem('token');
    return !this.jwtHelper.isTokenExpired(token);
  }

  getToken(){
    return this.jwtHelper.decodeToken(localStorage.getItem('token'));
  }

  logout() {
    localStorage.clear();
  }

  login(model: any) {
    return this.httpClient.post<any>(this.baseUrl + 'login', model, { observe: 'response' })
      .subscribe(resp => {
        if (resp) {
          console.log(model);
          const token = resp.headers.get('Authorization');
          localStorage.setItem('token', token);
        }
      });
  }
}
