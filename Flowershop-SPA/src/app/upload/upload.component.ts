import { Component, OnInit } from '@angular/core';
import { FlowerService } from '../_service/flower.service';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent implements OnInit {

  model: any = {};
  selectedFile: File;

  constructor(private flowerService: FlowerService) { }

  upload() {
      this.model.image = this.selectedFile;
      this.flowerService.uploadFlower(this.model);
  }

  onFileChanged(event) {
   this.selectedFile = event.target.files[0];
  }

  ngOnInit() {
  }

}
