import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent implements OnInit {

  model: any = {};
  selectedFile: File;

  constructor() { }

  upload() {
      this.model.image = this.selectedFile;
      this.http.post()
  }

  onFileChanged(event) {
   this.selectedFile = event.target.files[0];
  }

  ngOnInit() {
  }

}
