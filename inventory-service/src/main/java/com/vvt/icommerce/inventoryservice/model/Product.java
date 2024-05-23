package com.vvt.icommerce.inventoryservice.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Setter @Getter @NoArgsConstructor

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Product name is required.")
    @Basic(optional = false)
    private String name;

    private Double price;

    private String pictureId;

    private String brand;

    private String color;

    private String description;

    @Builder
    public Product(Long id, String name, double price, String pictureId, String brand, String color, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.pictureId = pictureId;
        this.brand = brand;
        this.color = color;
        this.description = description;
    }

}