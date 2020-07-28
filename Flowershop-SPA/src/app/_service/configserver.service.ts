import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class ConfigserverService {
  baseUrl: string;

constructor(private http: HttpClient) {
  this.baseUrl = 'http://localhost:8888/';
 }


public getPricefromProfile(flowerType: any, profile: any){
  return this.http.get(this.baseUrl + flowerType + '-' + profile + '.json');
}
}
