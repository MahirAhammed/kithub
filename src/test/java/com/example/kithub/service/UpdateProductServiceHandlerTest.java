package com.example.kithub.service;

import com.example.kithub.category.Category;
import com.example.kithub.category.CategoryRepository;
import com.example.kithub.exceptions.ProductNotFoundException;
import com.example.kithub.product.Product;
import com.example.kithub.product.ProductRepository;
import com.example.kithub.product.ProductRequest;
import com.example.kithub.product.Region;
import com.example.kithub.product.commandhandlers.UpdateProductHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

public class UpdateProductServiceHandlerTest {

    @InjectMocks
    private UpdateProductHandler updateProductHandler;

    @Mock
    private ProductRepository productRepo;

    @Mock
    private CategoryRepository categoryRepo;

    private Product product;
    private Category category;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);

        UUID id = UUID.randomUUID();
        category =  new Category("category1");
        category.setCategoryId(1);

        product = new Product();
        product.setProductId(id);
        product.setProductName("Test product");
        product.setDescription("Description of the test product");
        product.setPrice(1400);
        product.setCategory(category);
        product.setQuantity(10);
        product.setRegions(Arrays.asList(Region.AU, Region.CA));
    }

    @Test
    public void updateProductHandler_invalidId_throwsProductNotFoundException(){
        ProductRequest request = new ProductRequest(product.getProductId().toString(), product);
        Mockito.when(productRepo.findById(product.getProductId())).thenReturn(Optional.empty());

        Assertions.assertThrows(ProductNotFoundException.class, () -> updateProductHandler.execute(request));
        Mockito.verify(productRepo, Mockito.times(0)).save(product);
    }

    @Test
    public void updateProductHandler_updateSuccessfully_returnResponseEntity(){
        ProductRequest request = new ProductRequest(product.getProductId().toString(), product);
        Mockito.when(productRepo.findById(product.getProductId())).thenReturn(Optional.of(product));
        Mockito.when(categoryRepo.findAll()).thenReturn(Arrays.asList(category));
        ResponseEntity<Product> response = updateProductHandler.execute(request);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode() );
        Assertions.assertEquals(response.getBody().getProductId(), product.getProductId());

        Mockito.verify(productRepo, Mockito.times(1)).save(product);
    }
}
