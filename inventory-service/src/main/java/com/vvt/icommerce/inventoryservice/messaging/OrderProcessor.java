package com.vvt.icommerce.inventoryservice.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface OrderProcessor {

    String IN_ORDERS = "unprocessed_product_orders";
    String OUT_ORDERS = "processed_product_orders";
    String IN_CANCELLATION_ORDERS = "cancelled_product_orders";

    @Input(IN_ORDERS)
    SubscribableChannel inProductOrders();

    @Output(OUT_ORDERS)
    MessageChannel outProductOrders();

    @Input(IN_CANCELLATION_ORDERS)
    SubscribableChannel inCancellationOrders();

}

