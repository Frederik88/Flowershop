import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Flower } from '../_model/flower.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FlowerService {
  baseUrl: string;

  constructor(private http: HttpClient) {
    this.baseUrl = 'http://localhost:8080/flower';
  }

  public getFlower(flowerId: any) {
    return this.http.get<Flower>(this.baseUrl + '/' + flowerId);
  }

  public getFlowers(): Observable<Flower[]>{
    return this.http.get<Flower[]>(this.baseUrl);
  }

  public uploadFlower(flower: any) {

    return this.http.post(this.baseUrl + '/upload', flower, { observe: 'response' })
      .subscribe((response) => {
        console.log(flower);
        if (response.status === 200) {
          console.log('Image upload successfully');
        }
        else {
          console.log('Image upload failed');
        }
      });
  }

}
