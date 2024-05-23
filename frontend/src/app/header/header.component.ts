import { IcommerceService } from './../icommerce/services/icommerce.service';
import { IcommerceComponent } from './../icommerce/icommerce.component';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../icommerce/services';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent {
  keyword: string;

  constructor(
    private router: Router,
    private authenticationService: AuthService,
    private eCommerceService: IcommerceService
  ) {}

  hasAdminRole(): Boolean {
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    return currentUser.roles.includes('ADMIN');
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }

  search() {}

  reset() {
    this.eCommerceService.reset();
  }
}
