package com.vvt.icommerce.inventoryservice.service;

import com.vvt.icommerce.inventoryservice.model.Warehouse;
import com.vvt.icommerce.inventoryservice.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class WarehouseServiceImpl implements WarehouseService{
    @Autowired
    private WarehouseRepository warehouseRepository;

    @Override
    public Iterable<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    @Override
    public Warehouse getWarehouse(long id) {
        return warehouseRepository.findById(id).orElse(null);
    }

    @Override
    public Warehouse save(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

}
