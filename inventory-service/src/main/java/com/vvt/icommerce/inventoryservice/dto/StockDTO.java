package com.vvt.icommerce.inventoryservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class StockDTO {
    private Long id;
    private Long productId;
    private Long warehouseId;
    private Long quantity;

    public StockDTO(Long id, Long productId, Long warehouseId, Long quantity) {
        this.id = id;
        this.productId = productId;
        this.warehouseId = warehouseId;
        this.quantity = quantity;
    }
}
