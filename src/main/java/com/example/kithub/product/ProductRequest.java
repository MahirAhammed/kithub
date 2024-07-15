package com.example.kithub.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    private String region;
    private String nameOrDescription;
    private String category;
    private String orderBy;
    private String supplier;

}
