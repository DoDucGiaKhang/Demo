package com.vvt.icommerce.inventoryservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class WarehouseDTO {
    private Long id;
    private String address;

    public WarehouseDTO(long id, String address) {
        this.id = id;
        this.address = address;
    }
}
