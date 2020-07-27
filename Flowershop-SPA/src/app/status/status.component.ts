import { Component, OnInit } from '@angular/core';
import { StatusService } from '../_service/status.service';
import { Bean } from '../_model/bean.model';

@Component({
  selector: 'app-status',
  templateUrl: './status.component.html',
  styleUrls: ['./status.component.css']
})
export class StatusComponent implements OnInit {
  info: any;
  health: any;
  beans: Bean[];

  constructor(private statusService: StatusService) { }

  ngOnInit() {
  }

  getInfo() {
    this.statusService.getInfo().subscribe(
      (response => {
        if (response) {
          this.info = response;
        }
        else {
          console.log('Actuator endpoint info not available');
        }
      })
    );
  }

  getHealth() {
    this.statusService.getHealth().subscribe(
      (response => {
        if (response) {
          this.health = response;
        }
        else {
          console.log('Actuator endpoint health not available');
        }
      })
    );
  }

  getBeans() {
    this.statusService.getBeans().subscribe(
      (response => {
        if (response) {
          this.info = response;
        }
        else {
          console.log('Endpoint not available');
        }
      })
    );
  }

}
