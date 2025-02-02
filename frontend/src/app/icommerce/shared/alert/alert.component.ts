import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';

import { AlertService } from '../../services';

@Component({ selector: 'alert', templateUrl: 'alert.component.html' })
export class AlertComponent implements OnInit, OnDestroy {
  private subscription: Subscription;
  message: any;

  constructor(private alertService: AlertService) {}

  ngOnInit() {
    this.subscription = this.alertService.getAlert().subscribe((message) => {
      switch (message && message.type) {
        case 'success':
          message.cssClass = 'alert alert-success';
          break;
        case 'out_of_stock':
          message.cssClass = 'alert alert-out-of-stock';
          break;
        case 'not-enough-fund':
          message.cssClass = 'alert alert-not-enough-fund';
          break;
        case 'error':
          message.cssClass = 'alert alert-danger';
          break;
      }

      this.message = message;
    });
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
}
