package com.vvt.icommerce.inventoryservice.service;

import com.vvt.icommerce.inventoryservice.messaging.OrderMessage;
import com.vvt.icommerce.inventoryservice.messaging.OrderProcessor;
import com.vvt.icommerce.inventoryservice.model.Order;
import com.vvt.icommerce.inventoryservice.model.OrderStatus;
import com.vvt.icommerce.inventoryservice.model.ProductOrder;
import com.vvt.icommerce.inventoryservice.repository.OrderRepository;
import com.vvt.icommerce.inventoryservice.repository.ProductOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class InventoryServiceImpl {
    @Autowired
    private StockService stockService;

    @Autowired
    private OrderProcessor orderProcessor;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductOrderRepository productOrderRepository;

    @StreamListener(OrderProcessor.IN_ORDERS)
    public void processOrder(Order order) {
        log.info("Pulling order: {} with status {}", order.getId(),  order.getStatus());
        Boolean available = true;
        for (ProductOrder productOrder : order.getProductOrders()) {
            productOrderRepository.save(productOrder);
            if(productOrder.getQuantity() > stockService.getTotalStock(productOrder.getProductId())) {
                order.setStatus(OrderStatus.OUT_OF_STOCK.name());
                available = false;
            }
        }
        if (available) order.setStatus(OrderStatus.AVAILABLE.name());
        orderRepository.save(order);
        OrderMessage orderMessage = new OrderMessage(order);
        log.info("Publishing order status: {}, id: {}", order.getStatus(), order.getId());
        orderProcessor.outProductOrders().send(orderMessage.getMessage());
    }

    @StreamListener(OrderProcessor.IN_CANCELLATION_ORDERS)
    public void cancelOrder(Order order) {
        log.info("Pulling order: {} with status {}", order.getId(),  order.getStatus());
        order.setStatus(OrderStatus.CANCELLED.name());
        orderRepository.save(order);
    }
}
