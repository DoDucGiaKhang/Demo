import { Component, OnInit } from '@angular/core';
import { User } from '../models/user.model';
import { AuthService } from '../shared/auth/auth.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss'],
})
export class UserComponent implements OnInit {
  currentUser: User;

  constructor(private authService: AuthService) {
    this.currentUser = this.authService.currentUserValue;
  }

  ngOnInit() {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
  }

  getAvatar() {
    return 'data:image/jpeg;base64,' + this.currentUser.avatarData;
  }
}
