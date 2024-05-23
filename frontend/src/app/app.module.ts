import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { JwtModule } from '@auth0/angular-jwt';

import { appRoutingModule } from './app.routing';
import { AppComponent } from './app.component';
import { IcommerceComponent } from './icommerce/icommerce.component';
import { ProductsComponent } from './icommerce/products/products.component';
import { ShoppingCartComponent } from './icommerce/orders/shopping-cart/shopping-cart.component';
import { UserComponent } from './icommerce/user/user.component';
import { LoginComponent } from './icommerce/shared/login/login.component';
import { RegisterComponent } from './icommerce/shared/register/register.component';
import { AlertComponent } from './icommerce/shared/alert/alert.component';
import { OrdersComponent } from './icommerce/orders/orders.component';
import { IcommerceService } from './icommerce/services';
import { AuthInterceptor } from './icommerce/shared/auth';
import { OrdersListComponent } from './icommerce/orders/orders-list/orders-list.component';
import { ProductDetailComponent } from './icommerce/products/product-detail/product-detail.component';
import { HeaderComponent } from './header/header.component';
import { ProductEditComponent } from './icommerce/products/product-edit/product-edit.component';

export function tokenGetter() {
  return localStorage.getItem('access_token');
}

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    IcommerceComponent,
    ProductsComponent,
    ShoppingCartComponent,
    OrdersComponent,
    UserComponent,
    LoginComponent,
    RegisterComponent,
    AlertComponent,
    OrdersListComponent,
    ProductDetailComponent,
    ProductEditComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: tokenGetter,
        allowedDomains: ['example.com'],
        disallowedRoutes: ['http://example.com/examplebadroute/'],
      },
    }),
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    appRoutingModule,
  ],
  providers: [
    IcommerceService,
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
