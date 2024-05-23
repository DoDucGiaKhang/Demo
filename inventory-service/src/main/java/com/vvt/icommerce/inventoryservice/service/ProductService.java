package com.vvt.icommerce.inventoryservice.service;

import com.vvt.icommerce.inventoryservice.model.Product;

public interface ProductService {
    public Iterable<Product> getAllProducts(String keyword);

    public Product getProduct(long id);

    public Product save(Product product);
}
