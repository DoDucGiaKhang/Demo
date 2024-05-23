package com.vvt.icommerce.orderservice.repository;

import com.vvt.icommerce.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.userId = ?1")
    public List<Order> getOrdersByUserId(Long userId);

}
