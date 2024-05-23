package com.vvt.icommerce.inventoryservice.service;

import com.vvt.icommerce.inventoryservice.exception.ResourceNotFoundException;
import com.vvt.icommerce.inventoryservice.messaging.OrderProcessor;
import com.vvt.icommerce.inventoryservice.model.Order;
import com.vvt.icommerce.inventoryservice.model.Product;
import com.vvt.icommerce.inventoryservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Iterable<Product> getAllProducts(String keyword) {
        if (keyword != null) {
            return productRepository.search(keyword);
        }
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(long id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

}
