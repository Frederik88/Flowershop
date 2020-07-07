import { Component, OnInit, Input, NgZone } from '@angular/core';
import { FlowerService } from '../_service/flower.service';
import { Flower } from '../_model/flower.model';

@Component({
  selector: 'app-flower',
  templateUrl: './flower.component.html',
  styleUrls: ['./flower.component.css']
})
export class FlowerComponent implements OnInit {
  @Input() flower: Flower;
  image: any;


  constructor(private flowerService: FlowerService, private ngZone: NgZone) { }


  ngOnInit() {
  }

  delete() {
    if (this.flower) {
      this.flowerService.deleteFlower(this.flower.id).subscribe(
        () => console.log('Flower successfully deleted'),
        (error) => console.log('error', error),
        () => {
          this.ngZone.run(() => {
          });
        }
      );
    }
  }
}
