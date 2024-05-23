package com.vvt.icommerce.orderservice.repository;


import com.vvt.icommerce.orderservice.model.PaymentOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentOrderRepository extends JpaRepository<PaymentOrder, Long> { }
