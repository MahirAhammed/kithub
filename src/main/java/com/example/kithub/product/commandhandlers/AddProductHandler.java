package com.example.kithub.product.commandhandlers;

import com.example.kithub.Command;
import com.example.kithub.product.Product;
import com.example.kithub.product.ProductDTO;
import com.example.kithub.product.ProductRepository;
import com.example.kithub.product.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddProductHandler implements Command<Product, ProductDTO> {

    private ProductRepository repository;

    @Autowired
    public AddProductHandler(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<ProductDTO> execute(Product product) {
        Optional<Product> existingProduct = repository.findByProductName(product.getProductName());

        if (existingProduct.isPresent()){
            throw new RuntimeException("Product already exists");
        }

        ProductValidator.validator(product);

        repository.save(product);

        return null;
    }
}
