package com.vvt.icommerce.orderservice.service;

import com.vvt.icommerce.orderservice.messaging.OrderProcessor;
import com.vvt.icommerce.orderservice.model.Order;
import com.vvt.icommerce.orderservice.model.OrderStatus;
import com.vvt.icommerce.orderservice.model.PaymentOrder;
import com.vvt.icommerce.orderservice.repository.OrderRepository;
import com.vvt.icommerce.orderservice.repository.PaymentOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentOrderRepository paymentOrderRepository;

    @Autowired
    private Map<Long, PaymentOrder> paymentOrderMap;

    @Autowired
    private Map<Long, Order> productOrderMap;

    @Override
    public Iterable<Order> getAllOrders(Long userId) {
        if (userId != null) {
            return this.orderRepository.getOrdersByUserId(userId);
        }
        return this.orderRepository.findAll();
    }

    @Override
    public Order create(Order order) {
        order.setDateCreated(LocalDate.now());
        return this.orderRepository.save(order);
    }

    @Override
    public Order update(Order order) {
        return this.orderRepository.save(order);
    }

    @Override
    public void update(PaymentOrder paymentOrder) {
        this.paymentOrderRepository.save(paymentOrder);
    }

    @Override
    public PaymentOrder getProcessedPaymentOrder(Long orderId) {
        return paymentOrderMap.get(orderId);
    }

    @Override
    public Order getProcessedProductOrder(Long orderId) {
        return productOrderMap.get(orderId);
    }

    @Override
    public PaymentOrder getPaymentOrder(Long orderId) {
        return paymentOrderRepository.getOne(orderId);
    }

    @Override
    public Optional<Order> getOrder(Long id) {
        return this.orderRepository.findById(id);
    }

    @StreamListener(OrderProcessor.IN_PAYMENT_ORDERS)
    public void processPaymentOrder(PaymentOrder paymentOrder) {
        log.info("Pulling payment order: {} {} for ${}", paymentOrder.getOrderId(), paymentOrder.getStatus(), paymentOrder.getTotalPrice());
        paymentOrderMap.put(paymentOrder.getOrderId(), paymentOrder);
    }

    @StreamListener(OrderProcessor.IN_PRODUCT_ORDERS)
    public void processProductOrder(Order order) {
        log.info("Pulling product order: {} {}", order.getId(), order.getStatus());
        productOrderMap.put(order.getId(), order);
    }


}