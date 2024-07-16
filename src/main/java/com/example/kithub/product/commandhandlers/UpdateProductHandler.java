package com.example.kithub.product.commandhandlers;

import com.example.kithub.Command;
import com.example.kithub.category.CategoryRepository;
import com.example.kithub.exceptions.InvalidUUIDException;
import com.example.kithub.exceptions.ProductNotFoundException;
import com.example.kithub.product.Product;
import com.example.kithub.product.ProductRepository;
import com.example.kithub.product.ProductRequest;
import com.example.kithub.product.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UpdateProductHandler implements Command<ProductRequest, Product> {

    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;


    @Autowired
    public UpdateProductHandler(ProductRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ResponseEntity<Product> execute(ProductRequest productRequest) {
        try{
            Optional<Product> existingProduct = repository.findById(UUID.fromString(productRequest.getId()));
            if (existingProduct.isEmpty()){
                throw new ProductNotFoundException();
            }
            productRequest.getProduct().setTimeCreated(existingProduct.get().getTimeCreated());
            ProductValidator.validator(productRequest.getProduct());
            ProductValidator.categoryValidator(productRequest.getProduct().getCategory(), categoryRepository.findAll());
            repository.save(productRequest.getProduct());

            return ResponseEntity.ok(productRequest.getProduct());
        }
        catch (IllegalArgumentException e){
            throw new InvalidUUIDException();
        }
    }
}
