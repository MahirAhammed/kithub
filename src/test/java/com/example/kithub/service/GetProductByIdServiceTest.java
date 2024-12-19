package com.example.kithub.service;

import com.example.kithub.category.Category;
import com.example.kithub.exceptions.ProductNotFoundException;
import com.example.kithub.product.Product;
import com.example.kithub.product.ProductDTO;
import com.example.kithub.product.ProductRepository;
import com.example.kithub.product.queryhandlers.GetProductByIdHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.UUID;

public class GetProductByIdServiceTest {

    @Mock   // dependencies needed for the test
    private ProductRepository repository;

    @InjectMocks // object being tested
    private GetProductByIdHandler getProductByIdHandler;

    @BeforeEach // pre-test initialisation
    public void setup(){
        // initialises the repo and service class
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void product_exists_service_returns_product_dto(){
        // given
        Product product = new Product();
        UUID id = UUID.randomUUID();
        product.setProductId(id);
        product.setProductName("Training shorts");
        product.setDescription("Comfortable shorts for sports and training");
        product.setPrice(400.00);
        product.setCategory(new Category("shorts"));

        Mockito.when(repository.findById(id)).thenReturn(Optional.of(product));

        // when
        ResponseEntity<ProductDTO> response = getProductByIdHandler.execute(String.valueOf(id));

        // then
        Assertions.assertNotNull(ResponseEntity.ok(new ProductDTO(product)));
        Assertions.assertEquals(ResponseEntity.ok(new ProductDTO(product)), response);
        Mockito.verify(repository, Mockito.times(1)).findById(id);
    }

    @Test
    public void product_not_found_throws_exception(){

        UUID id = UUID.randomUUID();
        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(ProductNotFoundException.class, () -> getProductByIdHandler.execute(String.valueOf(id)));

    }
}
