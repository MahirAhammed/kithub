package com.example.kithub.service;

import com.example.kithub.exceptions.ProductNotFoundException;
import com.example.kithub.product.Product;
import com.example.kithub.product.ProductRepository;
import com.example.kithub.product.commandhandlers.DeleteProductHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.UUID;

public class DeleteProductServiceHandlerTest {

    @InjectMocks
    private DeleteProductHandler deleteProductHandler;

    @Mock
    private ProductRepository repository;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deleteProductHandler_invalidId_throwsProductNotFoundException(){
        UUID id = UUID.randomUUID();
        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());
        Assertions.assertThrows(ProductNotFoundException.class, () -> deleteProductHandler.execute(id.toString()));
        Mockito.verify(repository, Mockito.times(0)).delete(new Product());
    }

    @Test
    public void deleteProductHandler_deleteProductSuccessfully_returnResponseEntity(){
        UUID id = UUID.randomUUID();
        Product product = new Product();
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(product));

        ResponseEntity response = deleteProductHandler.execute(id.toString());

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        Assertions.assertNull(response.getBody());
        Mockito.verify(repository, Mockito.times(1)).delete(product);
    }
}
