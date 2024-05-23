package com.vvt.icommerce.inventoryservice.repository;

import com.vvt.icommerce.inventoryservice.model.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
}
