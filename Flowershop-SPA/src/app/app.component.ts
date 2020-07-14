import { Component, OnInit } from '@angular/core';
import { FlowerService } from './_service/flower.service';
import { Flower } from './_model/flower.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  flowers: Flower[];

  constructor(private flowerService: FlowerService) { }

  ngOnInit(): void {
    /*
    this.flowerService.getFlowers().subscribe(data => {
      if (data) {
        this.flowers = data;
      }
      else {
        console.log('No flowers available');
      }
    });
    */
  }
}


