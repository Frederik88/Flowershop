import { Component, OnInit } from '@angular/core';
import { LoginService } from '../_service/login.service';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {
  userName: any;
  constructor(private loginService: LoginService) { }

  ngOnInit() {
    this.getUserNameFromToken();
  }

  public getUserNameFromToken() {
    const token = this.loginService.getToken();
    if (token) {
      this.userName = token.sub;
    }
  }

  public logout(){
    this.loginService.logout();
  }

}
