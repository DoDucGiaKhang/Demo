import { Component, OnInit } from '@angular/core';
import { Order } from '../../models/order.model';
import { IcommerceService } from '../../services';

@Component({
  selector: 'app-orders-list',
  templateUrl: './orders-list.component.html',
  styleUrls: ['./orders-list.component.scss'],
})
export class OrdersListComponent implements OnInit {
  orders: Order[] = [];

  constructor(private ecommerceService: IcommerceService) {}

  ngOnInit(): void {
    this.loadOrders();
  }

  loadOrders() {
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.ecommerceService.getOrdersByUserId(currentUser.id).subscribe(
      (orders: any[]) => {
        this.orders = orders;
      },
      (error) => console.log(error)
    );
  }
}
