package com.vvt.icommerce.inventoryservice.service;

import com.vvt.icommerce.inventoryservice.model.Stock;
import com.vvt.icommerce.inventoryservice.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StockServiceImpl implements StockService{
    @Autowired
    private StockRepository stockRepository;

    @Override
    public Iterable<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    @Override
    public Stock getStock(long id) {
        return stockRepository.findById(id).orElse(null);
    }

    @Override
    public Stock save(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    public Stock getStockByProductIdAndWarehouseID(Long productId, Long warehouseId) {
        return stockRepository.findByProductIdAndWarehouseId(productId, warehouseId);
    }

    @Override
    public Integer getTotalStock(Long productId) {
        List<Stock> stockList = stockRepository.findByProductId(productId);
        int total = 0;
        for (Stock stock: stockList) {
            total += stock.getQuantity();
        }
        return total;
    }
}
