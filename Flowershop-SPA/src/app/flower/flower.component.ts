import { Component, OnInit, Input, NgZone } from '@angular/core';
import { FlowerService } from '../_service/flower.service';
import { Flower } from '../_model/flower.model';
import { ConfigserverService } from '../_service/configserver.service';

@Component({
  selector: 'app-flower',
  templateUrl: './flower.component.html',
  styleUrls: ['./flower.component.css']
})
export class FlowerComponent implements OnInit {
  @Input() flower: Flower;
  image: any;
  price: any;


  constructor(private flowerService: FlowerService, private ngZone: NgZone, private configserver: ConfigserverService) {
  }


  ngOnInit() {
    console.log(this.flower.type);
    this.getPrice();

  }

  getPrice() {
    if (this.flower) {
      this.configserver.getPricefromProfile(this.flower.type, 'dev').subscribe(
        (response: { price: string }) => {
          if (response) {
            this.price = response.price;
          }
        },
        (error) => console.log('Error: ' + error)
      );
    }
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
