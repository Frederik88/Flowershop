import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../_model/User';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl: string;

  constructor(private http: HttpClient) {
    this.baseUrl = 'http://localhost:8080/user';
  }

  public findAll(): Observable<User[]> {
    return this.http.get<User[]>(this.baseUrl + '/users');
  }

  public createUser(user: User) {
    return this.http.post<User>(this.baseUrl + '/add', user);
  }
}
