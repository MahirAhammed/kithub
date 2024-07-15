package com.example.kithub.product.commandhandlers;

import com.example.kithub.Command;
import com.example.kithub.product.Product;
import com.example.kithub.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateProductHandler implements Command<Product, Product> {

    private final ProductRepository repository;

    @Autowired
    public UpdateProductHandler(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<Product> execute(Product product) {

        Optional<Product> existingProduct = repository.findById(product.getProductId());
        if (existingProduct.isEmpty()){
            throw new RuntimeException("Product does not exist");
        }
        product.setTimeCreated(existingProduct.get().getTimeCreated());

        repository.save(product);

        return ResponseEntity.ok(product);
    }
}
