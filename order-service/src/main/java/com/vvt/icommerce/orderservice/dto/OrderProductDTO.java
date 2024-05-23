package com.vvt.icommerce.orderservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class OrderProductDTO {

    private ProductDTO product;
    private Integer quantity;
}