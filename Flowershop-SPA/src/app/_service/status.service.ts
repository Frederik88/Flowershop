import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Bean } from '../_model/bean.model';

@Injectable({
  providedIn: 'root'
})
export class StatusService {

  private baseUrl: string;

  constructor(private http: HttpClient) {
    this.baseUrl = 'http://localhost:8080/actuator';
  }

  public getInfo(): Observable<JSON> {
    return this.http.get<JSON>(this.baseUrl + '/info');
  }

  public getBeans(): Observable<Bean[]>{
    return this.http.get<Bean[]>(this.baseUrl + '/beans');
  }

  public getHealth(): Observable<JSON>{
    return this.http.get<JSON>(this.baseUrl + '/health');
  }

}
