package com.vvt.icommerce.orderservice.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ProductDTO {

    private Long id;

    @NotNull(message = "Product name is required.")
    private String name;

    private Double price;

    private String pictureId;

    public ProductDTO(long id, String name, double price, String pictureId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.pictureId = pictureId;
    }
}