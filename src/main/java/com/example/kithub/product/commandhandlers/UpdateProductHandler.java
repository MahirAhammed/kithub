package com.example.kithub.product.commandhandlers;

import com.example.kithub.Command;
import com.example.kithub.category.CategoryRepository;
import com.example.kithub.exceptions.InvalidUUIDException;
import com.example.kithub.exceptions.ProductNotFoundException;
import com.example.kithub.product.Product;
import com.example.kithub.product.ProductRepository;
import com.example.kithub.product.ProductRequest;
import com.example.kithub.product.ProductValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UpdateProductHandler implements Command<ProductRequest, Product> {

    private static final Logger logger = LoggerFactory.getLogger(UpdateProductHandler.class);

    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;


    @Autowired
    public UpdateProductHandler(ProductRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ResponseEntity<Product> execute(ProductRequest productRequest) {
        logger.info("Executing " + getClass());
        try{
            UUID productId = UUID.fromString(productRequest.getId());
            Optional<Product> existingProduct = repository.findById(productId);

            if (existingProduct.isEmpty()){
                logger.error("Product does not exist");
                throw new ProductNotFoundException();
            }

            Product updatedProduct = productRequest.getProduct();

            updatedProduct.setProductId(productId);
            updatedProduct.setTimeCreated(existingProduct.get().getTimeCreated());

            if (updatedProduct.getProductName() == null){
                updatedProduct.setProductName(existingProduct.get().getProductName());
            }

            if (updatedProduct.getDescription() == null){
                updatedProduct.setDescription(existingProduct.get().getDescription());
            }

            if (updatedProduct.getPrice() <= 0){
                updatedProduct.setPrice(existingProduct.get().getPrice());
            }

            if (updatedProduct.getQuantity() <= 0){
                updatedProduct.setPrice(existingProduct.get().getQuantity());
            }

            if (updatedProduct.getCategory() == null){
                updatedProduct.setCategory(existingProduct.get().getCategory());
            }

            if (updatedProduct.getSupplier() == null){
                updatedProduct.setSupplier(existingProduct.get().getSupplier());
            }

            if (updatedProduct.getRegions() == null){
                updatedProduct.setRegions(existingProduct.get().getRegions());
            }


            ProductValidator.validator(updatedProduct);
            ProductValidator.categoryValidator(updatedProduct.getCategory(), categoryRepository.findAll());
            repository.save(updatedProduct);

            return ResponseEntity.ok(productRequest.getProduct());
        }
        catch (IllegalArgumentException e){
            logger.error("Invalid UUID provided");
            throw new InvalidUUIDException();
        }
    }
}
