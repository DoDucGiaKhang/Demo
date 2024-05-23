package com.vvt.icommerce.orderservice.messaging;

import com.vvt.icommerce.orderservice.model.Order;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

public class OrderMessage {
    private Order order;

    public OrderMessage(Order order) {
        this.order = order;
    }

    public Message getMessage() {
        return MessageBuilder.withPayload(order).build();
    }
}
