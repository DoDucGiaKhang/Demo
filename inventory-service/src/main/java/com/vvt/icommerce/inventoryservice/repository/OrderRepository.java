package com.vvt.icommerce.inventoryservice.repository;

import com.vvt.icommerce.inventoryservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
