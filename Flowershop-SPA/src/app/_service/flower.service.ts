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

  public getFlower(flowerId: any): Observable<Flower> {
    return this.http.get<Flower>(this.baseUrl + '/' + flowerId);
  }

  public getFlowers(): Observable<Flower[]>{
    return this.http.get<Flower[]>(this.baseUrl);
  }

  public deleteFlower(flowerId: any){
    return this.http.get(this.baseUrl + '/delete/' + flowerId);
  }

  public uploadFlower(flower: any) {
    const uploadData = new FormData();
    uploadData.append('name', flower.name);
    uploadData.append('type', flower.type);
    uploadData.append('image', flower.image);

    return this.http.post(this.baseUrl + '/upload', uploadData, { observe: 'response' })
      .subscribe((response) => {
        console.log(uploadData);
        if (response.status === 200) {
          console.log('Image upload successfully');
        }
        else {
          console.log('Image upload failed');
        }
      });
  }

}
