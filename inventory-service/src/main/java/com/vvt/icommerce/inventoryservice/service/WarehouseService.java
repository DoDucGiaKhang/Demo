package com.vvt.icommerce.inventoryservice.service;

import com.vvt.icommerce.inventoryservice.model.Warehouse;

public interface WarehouseService {
    public Iterable<Warehouse> getAllWarehouses();

    public Warehouse getWarehouse(long id);

    public Warehouse save(Warehouse warehouse);
}
