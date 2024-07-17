package com.example.kithub.product.commandhandlers;

import com.example.kithub.Command;
import com.example.kithub.exceptions.InvalidUUIDException;
import com.example.kithub.exceptions.ProductNotFoundException;
import com.example.kithub.product.Product;
import com.example.kithub.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DeleteProductHandler implements Command<String, Void>{

    private final ProductRepository repository;

    @Autowired
    public DeleteProductHandler(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<Void> execute(String id) {
        try{
            Optional<Product> product = repository.findById(UUID.fromString(id));
            if (product.isEmpty()){
                throw new ProductNotFoundException();
            }

            repository.delete(product.get());

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        catch (IllegalArgumentException e){
            throw new InvalidUUIDException();
        }

    }
}

