package com.vvt.icommerce.paymentservice.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface OrderProcessor {

    String IN_ORDERS = "unprocessed_payment_orders";
    String OUT_ORDERS = "processed_payment_orders";
    String IN_CANCELLATION_ORDERS = "cancelled_payment_orders";

    @Input(IN_ORDERS)
    SubscribableChannel inPaymentOrders();

    @Output(OUT_ORDERS)
    MessageChannel outPaymentOrders();

    @Input(IN_CANCELLATION_ORDERS)
    SubscribableChannel inCancellationOrders();

}

