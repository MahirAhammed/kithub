package com.example.kithub.product.commandhandlers;

import com.example.kithub.Command;
import com.example.kithub.category.CategoryRepository;
import com.example.kithub.product.Product;
import com.example.kithub.product.ProductDTO;
import com.example.kithub.product.ProductRepository;
import com.example.kithub.product.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddProductHandler implements Command<Product, ProductDTO> {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public AddProductHandler(ProductRepository repository, CategoryRepository categoryRepository) {
        this.productRepository = repository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ResponseEntity<ProductDTO> execute(Product product) {
        Optional<Product> existingProduct = productRepository.findByProductName(product.getProductName());

        if (existingProduct.isPresent()){
            throw new RuntimeException("Product already exists");
        }

        ProductValidator.validator(product);
        ProductValidator.categoryValidator(product.getCategory(), categoryRepository.findAll());

        productRepository.save(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ProductDTO(product));
    }
}
