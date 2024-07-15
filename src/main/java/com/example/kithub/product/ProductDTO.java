package com.example.kithub.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private String productName;
    private String description;
    private double price;
    private int quantity;
    private String category;
    private String supplier;
    private List<Region> regions;

    public ProductDTO(Product product){
        this.productName = product.getProductName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.category = product.getCategory().getValue();
        this.supplier = product.getSupplier();
        this.regions = product.getRegions();
    }

}
