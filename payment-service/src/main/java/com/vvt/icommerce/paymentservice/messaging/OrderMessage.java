package com.vvt.icommerce.paymentservice.messaging;

import com.vvt.icommerce.paymentservice.model.PaymentOrder;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

public class OrderMessage {
    private PaymentOrder order;

    public OrderMessage(PaymentOrder order) {
        this.order = order;
    }

    public Message getMessage() {
        return MessageBuilder.withPayload(order).build();
    }
}
