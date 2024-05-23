package com.vvt.icommerce.orderservice.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface OrderProcessor {

    String IN_PAYMENT_ORDERS = "processed_payment_orders";
    String OUT_PAYMENT_ORDERS = "unprocessed_payment_orders";
    String OUT_CANCELLED_PAYMENT_ORDERS = "cancelled_payment_orders";
    String OUT_CANCELLED_PRODUCT_ORDERS = "cancelled_product_orders";
    String IN_PRODUCT_ORDERS = "processed_product_orders";
    String OUT_PRODUCT_ORDERS = "unprocessed_product_orders";

    @Input(IN_PAYMENT_ORDERS)
    SubscribableChannel inPaymentOrders();

    @Output(OUT_PAYMENT_ORDERS)
    MessageChannel outPaymentOrders();

    @Output(OUT_CANCELLED_PAYMENT_ORDERS)
    MessageChannel outCancelledPaymentOrders();

    @Output(OUT_CANCELLED_PRODUCT_ORDERS)
    MessageChannel outCancelledProductOrders();

    @Input(IN_PRODUCT_ORDERS)
    SubscribableChannel inProductOrders();

    @Output(OUT_PRODUCT_ORDERS)
    MessageChannel outProductOrders();

}

