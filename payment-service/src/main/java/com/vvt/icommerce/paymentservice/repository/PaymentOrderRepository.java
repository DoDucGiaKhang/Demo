package com.vvt.icommerce.paymentservice.repository;

import com.vvt.icommerce.paymentservice.model.PaymentOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentOrderRepository extends JpaRepository<PaymentOrder, Long> {
}
