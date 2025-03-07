package com.example.kithub.product.commandhandlers;

import com.example.kithub.exceptions.InvalidFieldException;
import com.example.kithub.exceptions.ProductNotFoundException;
import com.example.kithub.product.Product;
import com.example.kithub.product.ProductDTO;
import com.example.kithub.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class InventoryHandler {

    private final ProductRepository repository;

    @Autowired
    public InventoryHandler(ProductRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<ProductDTO> incrementProduct(String id, int amount){

        Optional<Product> product = repository.findById(UUID.fromString(id));
        if (product.isEmpty()){
            throw new ProductNotFoundException();
        }

        int newAmount = product.get().getQuantity() + amount;
        product.get().setQuantity(newAmount);
        repository.save(product.get());

        return ResponseEntity.ok().body(new ProductDTO(product.get()));
    }

    public ResponseEntity<ProductDTO> decrementProduct(String id, int amount){

        Optional<Product> product = repository.findById(UUID.fromString(id));
        if (product.isEmpty()){
            throw new ProductNotFoundException();
        }

        int newAmount = product.get().getQuantity() + amount;
        if (newAmount < 0){
            throw new InvalidFieldException("Given amount to decrement is too big");
        }
        product.get().setQuantity(newAmount);
        repository.save(product.get());

        return ResponseEntity.ok().body(new ProductDTO(product.get()));
    }


}
