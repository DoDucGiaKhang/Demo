package com.vvt.icommerce.inventoryservice.service;

import com.vvt.icommerce.inventoryservice.model.Stock;

public interface StockService {
    public Iterable<Stock> getAllStocks();

    public Stock getStock(long id);

    public Stock save(Stock stock);

    public Stock getStockByProductIdAndWarehouseID(Long productId, Long warehouseId);

    public Integer getTotalStock(Long productId);
}
