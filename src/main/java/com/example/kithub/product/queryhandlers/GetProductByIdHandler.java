package com.example.kithub.product.queryhandlers;

import com.example.kithub.Query;
import com.example.kithub.exceptions.InvalidUUIDException;
import com.example.kithub.exceptions.ProductNotFoundException;
import com.example.kithub.product.Product;
import com.example.kithub.product.ProductDTO;
import com.example.kithub.product.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetProductByIdHandler implements Query<String, ProductDTO> {

    private static final Logger logger = LoggerFactory.getLogger(GetProductByIdHandler.class);
    private final ProductRepository repository;

    @Autowired
    public GetProductByIdHandler(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<ProductDTO> execute(String id) {

        try{
            logger.info("Executing {} with {}", getClass(), id);
            Optional<Product> product = repository.findById(UUID.fromString(id));

            if (product.isEmpty()){
                logger.error("Product does not exist");
                throw new ProductNotFoundException();
            }

            return ResponseEntity.status(HttpStatus.OK).body(new ProductDTO(product.get()));

        }catch (IllegalArgumentException e){
            logger.error("Invalid UUID provided");
            throw new InvalidUUIDException();
        }

    }
}
