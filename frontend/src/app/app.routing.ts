import { Routes, RouterModule } from '@angular/router';

import { UserComponent } from './icommerce/user';
import { LoginComponent } from './icommerce/shared/login';
import { RegisterComponent } from './icommerce/shared/register';
import { IcommerceComponent } from './icommerce/icommerce.component';
import { AuthGuardService as AuthGuard } from './icommerce/shared/auth/auth-guard.service';
import { OrdersListComponent } from './icommerce/orders/orders-list/orders-list.component';
import { ProductDetailComponent } from './icommerce/products/product-detail/product-detail.component';
import { ProductEditComponent } from './icommerce/products/product-edit/product-edit.component';

const routes: Routes = [
  { path: '', component: IcommerceComponent, canActivate: [AuthGuard] },
  { path: 'profile', component: UserComponent, canActivate: [AuthGuard] },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'orders', component: OrdersListComponent, canActivate: [AuthGuard] },
  {
    path: 'product-detail',
    component: ProductDetailComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'product-edit',
    component: ProductEditComponent,
    canActivate: [AuthGuard],
  },
  { path: '**', redirectTo: '' },
];

export const appRoutingModule = RouterModule.forRoot(routes);
