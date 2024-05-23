import { ProductOrder } from './../../models/product-order.model';
import { Product } from './../../models/product.model';
import { Component, OnInit } from '@angular/core';
import { IcommerceService } from '../../services';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.scss'],
})
export class ProductDetailComponent implements OnInit {
  order: ProductOrder;

  constructor(private ecommerceService: IcommerceService) {}

  ngOnInit(): void {
    this.order = this.ecommerceService.SelectedProductOrder;
  }

  getImage() {
    return 'data:image/jpeg;base64,' + this.order.product.pictureData;
  }

  addToCart() {
    this.ecommerceService.SelectedProductOrder = this.order;
  }

  removeFromCart() {
    let index = this.getProductIndex(this.order.product);
    let shoppingCartOrders = this.ecommerceService.ProductOrders;
    if (index > -1) {
      shoppingCartOrders.productOrders.splice(
        this.getProductIndex(this.order.product),
        1
      );
    }
    this.ecommerceService.ProductOrders = shoppingCartOrders;
  }

  getProductIndex(product: Product): number {
    return this.ecommerceService.ProductOrders.productOrders.findIndex(
      (value) => value.product === product
    );
  }

  hasAdminRole(): Boolean {
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    return currentUser.roles.includes('ADMIN');
  }

  changePrice(price) {
    this.ecommerceService.saveProduct(this.order.product).subscribe();
  }
}
