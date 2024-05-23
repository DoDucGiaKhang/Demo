package com.vvt.icommerce.orderservice.repository;

import com.vvt.icommerce.orderservice.model.ProductOrder;
import com.vvt.icommerce.orderservice.model.OrderProductPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<ProductOrder, OrderProductPK> {
}
