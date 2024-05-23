package com.vvt.icommerce.orderservice.controller;

import com.vvt.icommerce.orderservice.dto.OrderCancelationDTO;
import com.vvt.icommerce.orderservice.dto.OrderDTO;
import com.vvt.icommerce.orderservice.dto.OrderProductDTO;
import com.vvt.icommerce.orderservice.messaging.OrderMessage;
import com.vvt.icommerce.orderservice.messaging.OrderProcessor;
import com.vvt.icommerce.orderservice.messaging.PaymentOrderMessage;
import com.vvt.icommerce.orderservice.model.Order;
import com.vvt.icommerce.orderservice.model.ProductOrder;
import com.vvt.icommerce.orderservice.model.OrderStatus;
import com.vvt.icommerce.orderservice.model.PaymentOrder;
import com.vvt.icommerce.orderservice.service.OrderProductService;
import com.vvt.icommerce.orderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/orders")
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderProcessor orderProsessor;

    @Autowired
    private OrderProductService orderProductService;

    @GetMapping(value = { "", "/" })
    public @NotNull Iterable<Order> getOrders(@RequestParam(required = false) Long userId) {
        return orderService.getAllOrders(userId);
    }

    @GetMapping(value = {"/{id}" })
    public Order getOrder(@PathVariable Long id) {
        return orderService.getOrder(id).get();
    }

    private Order buildOrder(Long userId, OrderDTO orderDTO) {
        List<OrderProductDTO> productOrderDtos = orderDTO.getProductOrders();
        Order order = new Order();
        order.setStatus(OrderStatus.PENDING.name());
        order.setUserId(userId);
        order = this.orderService.create(order);

        List<ProductOrder> productOrders = new ArrayList<ProductOrder>();
        for (OrderProductDTO orderProductDto : productOrderDtos) {
            var productOrder = new ProductOrder(order,
                    orderProductDto.getProduct().getId(),
                    orderProductDto.getQuantity()
            );
            productOrder.setTotalPrice(orderProductDto.getProduct().getPrice() * orderProductDto.getQuantity());
            productOrders.add(orderProductService.create(productOrder));
        }

        order.setProductOrders(productOrders);
        return this.orderService.update(order);

    }

    private Order processPayment(Order order) {
        log.info("Publishing payment order: " + order.getId() + " " + order.getTotalOrderPrice());
        PaymentOrder paymentOrder = new PaymentOrder(order.getId(), order.getUserId(), order.getStatus(), order.getTotalOrderPrice());
        orderService.update(paymentOrder);
        PaymentOrderMessage orderMessage = new PaymentOrderMessage(paymentOrder);
        this.orderProsessor.outPaymentOrders().send(orderMessage.getMessage());

        while(true) {
            paymentOrder = orderService.getProcessedPaymentOrder(order.getId());
            if (paymentOrder != null) {
                orderService.update(paymentOrder);
                order = orderService.getOrder(order.getId()).orElse(null);
                order.setStatus(paymentOrder.getStatus());
                break;
            }
        }
        return orderService.update(order);
    }

    private Order processInventory(Order order) {
        log.info("Publishing product order: " + order.getId());
        Long orderId = order.getId();
        OrderMessage orderMessage = new OrderMessage(order);
        this.orderProsessor.outProductOrders().send(orderMessage.getMessage());

        while(true) {
            Order productOrder = orderService.getProcessedProductOrder(orderId);
            if (productOrder != null) {
                order.setStatus(productOrder.getStatus());
                break;
            }
        }
        return orderService.update(order);
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestAttribute String userId, @RequestBody OrderDTO orderDTO) {
        Order order = buildOrder(Long.valueOf(userId), orderDTO);
        Order inventoryOrder = processInventory(order);
        if (inventoryOrder.getStatus().equals(OrderStatus.AVAILABLE.name())) {
            Order paymentOrder = processPayment(order);
            return new ResponseEntity<>(paymentOrder, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(inventoryOrder, HttpStatus.CREATED);
        }
    }

    @PostMapping(path = {"/cancel"})
    public ResponseEntity<Order> cancel(@RequestBody OrderCancelationDTO cancelationRequest) {
        Order order = orderService.getOrder(cancelationRequest.getOrderId()).orElse(null);
        order.setStatus(OrderStatus.CANCELLED.name());
        orderService.update(order);
        PaymentOrder paymentOrder = orderService.getPaymentOrder(order.getId());
        log.info("Publishing cancelled order: " + paymentOrder.getOrderId());
        paymentOrder.setStatus(OrderStatus.CANCELLED.name());
        orderService.update(paymentOrder);
        this.orderProsessor.outCancelledPaymentOrders().send(new PaymentOrderMessage(paymentOrder).getMessage());
        this.orderProsessor.outCancelledProductOrders().send(new OrderMessage(order).getMessage());
        return new ResponseEntity<>(order, HttpStatus.ACCEPTED);
    }
}
