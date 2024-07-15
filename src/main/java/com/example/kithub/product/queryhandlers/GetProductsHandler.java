package com.example.kithub.product.queryhandlers;

import com.example.kithub.Query;
import com.example.kithub.product.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetProductsHandler implements Query<ProductRequest, List<ProductDTO>> {

    private final ProductRepository repository;

    @Autowired
    public GetProductsHandler(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<List<ProductDTO>> execute(ProductRequest input) {
        List<Product> products = repository.findProductsByMatchingCriteria(
                input.getNameOrDescription(),
                input.getRegion(),
                input.getCategory(),
                input.getSupplier()
            );

        if (input.getOrderBy() != null){
            switch (input.getOrderBy()) {
                case "name":
                    products.sort(Comparator.comparing(Product::getProductName));
                    break;
                case "price":
                    products.sort(Comparator.comparing(Product::getPrice));
                    break;

            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                products
                        .stream()
                        .map(ProductDTO::new)
                        .collect(Collectors.toList()));
    }
}
