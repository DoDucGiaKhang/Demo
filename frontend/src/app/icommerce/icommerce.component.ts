import { ProductDetailComponent } from './products/product-detail/product-detail.component';
import { Component, OnInit, ViewChild } from '@angular/core';
import { ProductsComponent } from './products';
import { ShoppingCartComponent } from './orders/shopping-cart';
import { OrdersComponent } from './orders';
import { Status } from './orders/status.component';
import { IcommerceService } from './services';

@Component({
  selector: 'app-icommerce',
  templateUrl: './icommerce.component.html',
  styleUrls: ['./icommerce.component.scss'],
})
export class IcommerceComponent implements OnInit {
  private collapsed = true;
  orderFinished = false;
  isOn = true;
  keyword: string;

  @ViewChild('productsC')
  productsC: ProductsComponent;

  @ViewChild('shoppingCartC')
  shoppingCartC: ShoppingCartComponent;

  @ViewChild('ordersC')
  ordersC: OrdersComponent;

  @ViewChild('productDetailC')
  productDetailC: ProductDetailComponent;

  constructor(private ecommerceService: IcommerceService) {}

  ngOnInit(): void {}

  toggleCollapsed(): void {
    this.collapsed = !this.collapsed;
  }

  finishOrder(orderFinished: boolean) {
    this.orderFinished = orderFinished;
  }

  reset() {
    this.orderFinished = false;
    this.productsC.reset();
    this.shoppingCartC.reset();
    this.ordersC.status = Status.UNKNOWN;
  }

  search() {
    if (this.productsC === undefined) return;
    this.productsC.search(this.keyword);
  }

  showProductDetail(): Boolean {
    return this.ecommerceService.ShowProductDetail;
  }
}
