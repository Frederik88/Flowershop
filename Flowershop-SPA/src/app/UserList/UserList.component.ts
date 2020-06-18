import { Component, OnInit } from '@angular/core';
import { UserService } from '../_service/User.service';
import { User } from '../_model/User';

@Component({
  selector: 'app-UserList',
  templateUrl: './UserList.component.html',
  styleUrls: ['./UserList.component.css']
})
export class UserListComponent implements OnInit {

  users: User[];

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.userService.findAll().subscribe(data => {
      this.users = data;
    });
  }

}
