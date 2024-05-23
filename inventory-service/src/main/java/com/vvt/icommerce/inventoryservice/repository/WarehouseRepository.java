package com.vvt.icommerce.inventoryservice.repository;

import com.vvt.icommerce.inventoryservice.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}
