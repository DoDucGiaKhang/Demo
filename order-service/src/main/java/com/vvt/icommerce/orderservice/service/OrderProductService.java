package com.vvt.icommerce.orderservice.service;

import com.vvt.icommerce.orderservice.model.ProductOrder;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface OrderProductService {
    ProductOrder create(@NotNull(message = "The products for order cannot be null.") @Valid ProductOrder orderProduct);
}
