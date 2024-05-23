package com.vvt.icommerce.inventoryservice.messaging;


import com.vvt.icommerce.inventoryservice.model.Order;
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
