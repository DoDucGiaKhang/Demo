package com.vvt.icommerce.inventoryservice.repository;

import com.vvt.icommerce.inventoryservice.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {
    public Stock findByProductIdAndWarehouseId(Long productId, Long warehouseID);
    public List<Stock> findByProductId(Long productId);
}
