package com.vvt.icommerce.inventoryservice.controller;

import com.vvt.icommerce.inventoryservice.dto.WarehouseDTO;
import com.vvt.icommerce.inventoryservice.model.Warehouse;
import com.vvt.icommerce.inventoryservice.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {
    @Autowired
    WarehouseService warehouseService;

    @GetMapping(value = { "", "/" })
    public @NotNull Iterable<Warehouse> getWarehouses() {
        return warehouseService.getAllWarehouses();
    }

    @GetMapping(value = {"/{id}"})
    public Warehouse getWarehouse(@PathVariable Long id) {
        return warehouseService.getWarehouse(id);
    }

    @PostMapping
    public ResponseEntity<Warehouse> updateWarehouse(@RequestBody WarehouseDTO warehouseDto){
        Warehouse warehouse = warehouseService.getWarehouse(warehouseDto.getId());
        warehouseService.save(warehouse);
        return new ResponseEntity(warehouse, HttpStatus.OK);
    }

    @PostMapping(value = {"/create"})
    public ResponseEntity<Warehouse> create(@RequestBody WarehouseDTO warehouseDTO) {
        Warehouse warehouse = Warehouse.builder().address(warehouseDTO.getAddress()).build();
        warehouseService.save(warehouse);
        return new ResponseEntity<>(warehouse, HttpStatus.CREATED);
    }
}
