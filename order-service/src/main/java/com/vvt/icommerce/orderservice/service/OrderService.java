package com.vvt.icommerce.orderservice.service;

import com.vvt.icommerce.orderservice.model.Order;
import com.vvt.icommerce.orderservice.model.PaymentOrder;

import java.util.Optional;

public interface OrderService {
    Iterable<Order> getAllOrders(Long userId);

    Optional<Order> getOrder(Long id);

    Order create(Order order);

    Order update(Order order);

    PaymentOrder getProcessedPaymentOrder(Long orderID);

    Order getProcessedProductOrder(Long orderID);

    PaymentOrder getPaymentOrder(Long orderID);

    void update(PaymentOrder paymentOrder);
}
