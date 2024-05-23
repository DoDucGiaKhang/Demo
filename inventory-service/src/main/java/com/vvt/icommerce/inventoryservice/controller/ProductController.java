package com.vvt.icommerce.inventoryservice.controller;

import com.vvt.icommerce.inventoryservice.dto.PhotoDTO;
import com.vvt.icommerce.inventoryservice.dto.ProductDTO;
import com.vvt.icommerce.inventoryservice.model.Product;
import com.vvt.icommerce.inventoryservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping(value = { "", "/" })
    public @NotNull Iterable<ProductDTO> getProducts(@RequestHeader("Authorization") String authHeader, @RequestParam(required = false) String keyword) {
        List<ProductDTO> productDtos = new ArrayList<ProductDTO>();
        Iterable<Product> products = productService.getAllProducts(keyword);
        for (Product product : products) {
            ProductDTO productDto = new ProductDTO(product);
            ResponseEntity<PhotoDTO> restExchange = null;
            if (product.getPictureId() != null) {
                try {
                    MultiValueMap<String, String> headers = new
                            LinkedMultiValueMap<String, String>();
                    headers.add("Authorization", authHeader);
                    headers.add("Content-Type", "application/json");
                    HttpEntity request = new HttpEntity(null, headers);
                    restExchange =
                            restTemplate.exchange(
                                    "http://localhost:4000/api/photos/{photoId}",
                                    HttpMethod.GET,
                                    request, PhotoDTO.class, product.getPictureId());
                } catch (HttpClientErrorException ex) {
                    if (ex.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                        return null;
                    }
                    throw ex;
                }
                PhotoDTO photoDto = restExchange.getBody();
                productDto.setPictureData(photoDto.getImage().getData());
            }
            productDtos.add(productDto);
        }
        return productDtos;
    }

    @GetMapping(value = {"/{id}"})
    public Product getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @PostMapping
    public ResponseEntity<Product> updateProduct(@RequestBody ProductDTO productDto){
        Product product = productService.getProduct(productDto.getId());
        if (productDto.getName() != null) product.setName(productDto.getName());
        if (productDto.getPrice() != null) product.setPrice(productDto.getPrice());
        if (productDto.getDescription() != null) product.setDescription(productDto.getDescription());
        if (productDto.getBrand() != null) product.setBrand(productDto.getBrand());
        if (productDto.getColor() != null) product.setColor(productDto.getColor());
        productService.save(product);
        return new ResponseEntity(product, HttpStatus.OK);
    }

    @PostMapping(value = {"/create"})
    public ResponseEntity<Product> create(@RequestBody ProductDTO productDTO) {
        Product product = Product.builder()
                .brand(productDTO.getBrand())
                .color(productDTO.getColor())
                .name(productDTO.getName())
                .pictureId(productDTO.getPictureId())
                .price(productDTO.getPrice())
                .description(productDTO.getDescription())
                .build();
        productService.save(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }
}
