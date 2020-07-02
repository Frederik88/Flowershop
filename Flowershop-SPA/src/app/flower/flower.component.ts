import { Component, OnInit } from '@angular/core';
import { FlowerService } from '../_service/flower.service';

@Component({
  selector: 'app-flower',
  templateUrl: './flower.component.html',
  styleUrls: ['./flower.component.css']
})
export class FlowerComponent implements OnInit {
  flower: any = {}

  constructor(private flowerService: FlowerService) { }

  public onFileChanged(event) {
    this.flower.img = event.target.files[0];
  }

  public onUpload() {
    console.log(this.flower.img);

    const uploadImageData = new FormData();
    uploadImageData.append('name', this.flower.name);
    uploadImageData.append('type', this.flower.type);
    uploadImageData.append('imageFile', this.flower.img);

    this.flowerService.uploadFlower(uploadImageData);
  }

  ngOnInit() {
  }

}
