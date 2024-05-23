package com.vvt.icommerce.inventoryservice.dto;

import com.vvt.icommerce.inventoryservice.model.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.Binary;

@Setter @Getter @NoArgsConstructor
public class ProductDTO {

    private Long id;

    private String name;

    private Double price;

    private String pictureId;

    private String brand;

    private String color;

    private String description;

    private byte[] pictureData;

    public ProductDTO(long id, String name, double price, String pictureId, String brand, String color, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.pictureId = pictureId;
        this.color = color;
        this.description = description;
    }

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.brand = product.getBrand();
        this.pictureId = product.getPictureId();
        this.color = product.getColor();
        this.description = product.getDescription();
    }

}