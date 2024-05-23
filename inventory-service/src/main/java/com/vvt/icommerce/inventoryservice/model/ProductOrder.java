package com.vvt.icommerce.inventoryservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Setter @Getter @NoArgsConstructor
public class ProductOrder {

    @Id
    private Long productId;

    private Long orderId;

    @Column(nullable = false)
    private Integer quantity;

    public ProductOrder(Long orderId, Long productId, Integer quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }
}
