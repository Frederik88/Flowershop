import { Component, OnInit } from '@angular/core';
import { UserService } from '../_service/User.service';
import { LoginService } from '../_service/login.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  model: any = {};
  loginExists: boolean;
  userName: any;

  constructor(private userService: UserService, private loginService: LoginService) { }

  public ngOnInit() {
    this.loggedIn();
    this.getUserNameFromToken();
  }

  public getUserNameFromToken() {
    const token = this.loginService.getToken();
    if (token) {
      this.userName = token.sub;
    }
  }

  public loggedIn() {
    this.loginExists = this.loginService.loggedIn();
  }

  public userExists() {
    if (this.userService.findAll()) {
      return true;
    }
    else {
      return false;
    }
  }

  public create() {
    if (this.model) {
      this.userService.createUser(this.model);
    }
  }

  public login() {
    console.log(this.model);
    this.loginService.login(this.model);
  }
}
