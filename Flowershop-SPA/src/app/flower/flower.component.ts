import { Component, OnInit, Input } from '@angular/core';
import { FlowerService } from '../_service/flower.service';
import { Flower } from '../_model/flower.model';

@Component({
  selector: 'app-flower',
  templateUrl: './flower.component.html',
  styleUrls: ['./flower.component.css']
})
export class FlowerComponent implements OnInit {
  @Input() flower: Flower;

  constructor() { }


  ngOnInit() {
  }

}
