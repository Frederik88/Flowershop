import { Component, OnInit } from '@angular/core';
import { FlowerService } from './_service/flower.service';
import { Flower } from './_model/flower.model';
import { LoginService } from './_service/login.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  flowers: Flower[];
  loginExists: boolean;

  constructor(private flowerService: FlowerService, private loginService: LoginService) { }

  public loggedIn() {
    this.loginExists = this.loginService.loggedIn();
  }

  ngOnInit(): void {
    this.loggedIn();
    if (this.loginExists) {
      this.flowerService.getFlowers().subscribe(data => {
        if (data) {
          this.flowers = data;
        }
        else {
          console.log('No flowers available');
        }
      });
    }
  }
}


