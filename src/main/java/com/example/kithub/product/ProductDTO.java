package com.example.kithub.product;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private String productId;
    private String productName;
    private String description;
    private double price;
    private int quantity;
    private String category;
    private String supplier;
    private List<Region> availableRegions;

    public ProductDTO(Product product){
        this.productId = product.getProductId().toString();
        this.productName = product.getProductName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.category = product.getCategory().getCategoryName();
        this.supplier = product.getSupplier();
        this.availableRegions = product.getRegions();
    }

}
