package com.vvt.icommerce.orderservice.messaging;

import com.vvt.icommerce.orderservice.model.Order;
import com.vvt.icommerce.orderservice.model.PaymentOrder;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

public class PaymentOrderMessage {
    private PaymentOrder order;

    public PaymentOrderMessage(PaymentOrder order) {
        this.order = order;
    }

    public Message getMessage() {
        return MessageBuilder.withPayload(order).build();
    }
}
