import { Component, OnInit } from '@angular/core';
import { ProductOrders } from '../models/product-orders.model';
import { IcommerceService } from '../services';
import { Subscription } from 'rxjs';
import { Status } from './status.component'

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.scss']
})


export class OrdersComponent implements OnInit {
  orders: ProductOrders;
  total: number;
  status: Status;
  sub: Subscription;
  StatusType = Status;

  constructor(private ecommerceService: IcommerceService) {
    this.orders = this.ecommerceService.ProductOrders;
    this.total = 0;
    this.status = Status.UNKNOWN;
    this.sub = new Subscription();
  }

  ngOnInit() {
    this.status = Status.UNKNOWN;
    this.sub = this.ecommerceService.OrdersChanged.subscribe(() => {
      this.orders = this.ecommerceService.ProductOrders;
    });
    this.loadTotal();
  }

  pay() {
    
    this.ecommerceService.saveOrder(this.orders)
    .subscribe(
        data => {
          switch (data["status"]) {
            case 'PAID': 
              this.status = Status.PAID;
              break;
            case 'OUT_OF_STOCK':
              this.status = Status.OUT_OF_STOCK;
              break;
            case 'NOT_ENOUGH_FUND':
              this.status = Status.NOT_ENOUGH_FUND
              break;
            default:
              this.status = Status.REJECTED
              break;
          }
        },
        error => {
            this.status = Status.REJECTED;
        });
  }

  loadTotal() {
    this.sub = this.ecommerceService.TotalChanged.subscribe(() => {
      this.total = this.ecommerceService.Total;
    });
  }
}
