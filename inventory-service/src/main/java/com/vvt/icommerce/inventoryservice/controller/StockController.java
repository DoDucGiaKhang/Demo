package com.vvt.icommerce.inventoryservice.controller;

import com.vvt.icommerce.inventoryservice.dto.StockDTO;
import com.vvt.icommerce.inventoryservice.model.Product;
import com.vvt.icommerce.inventoryservice.model.Stock;
import com.vvt.icommerce.inventoryservice.service.StockService;
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
@RequestMapping("/api/stocks")
public class StockController {
    @Autowired
    StockService stockService;

    @GetMapping(value = { "", "/" })
    public @NotNull Iterable<Stock> getStocks() {
        return stockService.getAllStocks();
    }

    @GetMapping(value = {"/{id}"})
    public Stock getStock(@PathVariable Long id) {
        return stockService.getStock(id);
    }

    @PostMapping
    public ResponseEntity<Product> updateStock(@RequestBody StockDTO stockDto){
        Stock stock = stockService.getStock(stockDto.getId());
        stockService.save(stock);
        return new ResponseEntity(stock, HttpStatus.OK);
    }

    @PostMapping(value = {"/allocate"})
    public ResponseEntity<Stock> create(@RequestBody StockDTO stockDTO) {
        Stock stock = stockService.getStockByProductIdAndWarehouseID(stockDTO.getProductId(), stockDTO.getWarehouseId());
        if (stock == null) {
            stock = Stock.builder()
                    .productId(stockDTO.getProductId())
                    .warehouseId(stockDTO.getWarehouseId())
                    .quantity(stockDTO.getQuantity())
                    .build();
        } else {
            stock.setQuantity(stock.getQuantity() + stockDTO.getQuantity());
        }
        stockService.save(stock);
        return new ResponseEntity<>(stock, HttpStatus.ACCEPTED);
    }

}
