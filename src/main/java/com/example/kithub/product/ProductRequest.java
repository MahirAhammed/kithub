package com.example.kithub.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ProductRequest {

    private String id;
    private Product product;
    private String region;
    private String nameOrDescription;
    private String category;
    private String orderBy;
    private String supplier;

    public ProductRequest(String region, String nameOrDescription, String category, String orderBy, String supplier){
        this.region = region;
        this.nameOrDescription = nameOrDescription;
        this.category = category;
        this.orderBy = orderBy;
        this.supplier = supplier;
    }

    public ProductRequest(String id, Product product){
        this.product = product;
        this.id = id;
    }

}
