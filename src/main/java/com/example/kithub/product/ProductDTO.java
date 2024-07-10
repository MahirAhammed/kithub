package com.example.kithub.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

}
