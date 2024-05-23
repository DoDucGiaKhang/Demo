import { Product } from './../models/product.model';
import { Component, OnInit } from '@angular/core';
import { ProductOrder } from '../models/product-order.model';
import { IcommerceService } from '../services';
import { Subscription } from 'rxjs/internal/Subscription';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss'],
})
export class ProductsComponent implements OnInit {
  productOrders: ProductOrder[] = [];
  products: Product[] = [];
  selectedProductOrder: ProductOrder;
  sub: Subscription;
  productSelected: boolean = false;

  constructor(private ecommerceService: IcommerceService) {}

  ngOnInit() {
    this.productOrders = [];
    this.loadProducts();
  }

  getProductIndex(product: Product): number {
    return this.ecommerceService.ProductOrders.productOrders.findIndex(
      (value) => value.product === product
    );
  }

  isProductSelected(product: Product): boolean {
    return this.getProductIndex(product) > -1;
  }

  loadProducts() {
    this.ecommerceService.getAllProducts().subscribe(
      (products: any[]) => {
        this.products = products;
        this.products.forEach((product) => {
          this.productOrders.push(new ProductOrder(product, 0));
        });
      },
      (error) => console.log(error)
    );
  }

  search(keyword) {
    this.productOrders = [];
    this.ecommerceService.search(keyword).subscribe(
      (products: any[]) => {
        this.products = products;
        this.products.forEach((product) => {
          this.productOrders.push(new ProductOrder(product, 0));
        });
      },
      (error) => console.log(error)
    );
  }

  changePrice(order: ProductOrder, price) {
    order.product.price = price.value;
    this.ecommerceService.saveProduct(order.product).subscribe();
  }

  reset() {
    this.productOrders = [];
    this.loadProducts();
    this.ecommerceService.ProductOrders.productOrders = [];
  }

  getImage(product: Product) {
    return 'data:image/jpeg;base64,' + product.pictureData;
  }

  hasAdminRole(): Boolean {
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    return currentUser.roles.includes('ADMIN');
  }

  selectProduct(order: ProductOrder) {
    this.selectedProductOrder = order;
    this.ecommerceService.SelectedProductOrder = order;
    this.ecommerceService.ShowProductDetail = true;
  }
}
