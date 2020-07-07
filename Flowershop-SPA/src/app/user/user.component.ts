import { Component, OnInit } from '@angular/core';
import { UserService } from '../_service/User.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  model: any = {};

  constructor(private userService: UserService) { }

  public ngOnInit() {
  }

  public loggedIn() {
    return true;
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
    
  }

}
