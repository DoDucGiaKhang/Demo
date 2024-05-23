import { Product } from './../models/product.model';
import { ProductOrder } from '../models/product-order.model';
import { Subject } from 'rxjs/internal/Subject';
import { ProductOrders } from '../models/product-orders.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class IcommerceService {
  private productsUrl = '/api/products';
  private ordersUrl = '/api/orders';

  private productOrder: ProductOrder;
  private orders: ProductOrders = new ProductOrders();
  private productOrderSubject = new Subject();
  private ordersSubject = new Subject();
  private totalSubject = new Subject();
  private total: number;
  private showProductDetail: Boolean;

  ProductOrderChanged = this.productOrderSubject.asObservable();
  OrdersChanged = this.ordersSubject.asObservable();
  TotalChanged = this.totalSubject.asObservable();

  constructor(private http: HttpClient) {
    this.total = 0;
  }

  getAllProducts() {
    return this.http.get(this.productsUrl);
  }

  getOrdersByUserId(userId: string) {
    return this.http.get(`${this.ordersUrl}?userId=${userId}`);
  }

  search(keyword: string) {
    return this.http.get(`${this.productsUrl}?keyword=${keyword}`);
  }

  saveProduct(product: Product) {
    return this.http.post(this.productsUrl, product);
  }

  saveOrder(order: ProductOrders) {
    const productOrders = order.productOrders.map((productOrder) => {
      const { pictureData, ...productDto } = productOrder.product;
      return { product: productDto, quantity: productOrder.quantity };
    });

    return this.http.post(this.ordersUrl, { productOrders: productOrders });
  }

  set SelectedProductOrder(value: ProductOrder) {
    this.productOrder = value;
    this.productOrderSubject.next();
  }

  get SelectedProductOrder() {
    return this.productOrder;
  }

  set ProductOrders(value: ProductOrders) {
    this.orders = value;
    this.ordersSubject.next();
  }

  get ProductOrders() {
    return this.orders;
  }

  get Total() {
    return this.total;
  }

  set Total(value: number) {
    this.total = value;
    this.totalSubject.next();
  }

  get ShowProductDetail() {
    return this.showProductDetail;
  }

  set ShowProductDetail(value: Boolean) {
    this.showProductDetail = value;
  }

  reset() {
    this.ShowProductDetail = false;
    this.productOrder = null;
  }
}
