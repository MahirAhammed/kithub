package com.example.kithub.service;

import com.example.kithub.category.Category;
import com.example.kithub.category.CategoryRepository;
import com.example.kithub.exceptions.InvalidFieldException;
import com.example.kithub.product.Product;
import com.example.kithub.product.ProductDTO;
import com.example.kithub.product.ProductRepository;
import com.example.kithub.product.Region;
import com.example.kithub.product.commandhandlers.AddProductHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

public class AddProductServiceHandlerTest {

    private Product product;
    private Category category1, category2, category3;

    @Mock
    private ProductRepository productRepo;

    @Mock
    private CategoryRepository categoryRepo;

    @InjectMocks
    private AddProductHandler addProductHandler;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);

        category1 = new Category("category1");
        category1.setCategoryId(1);
        category2 = new Category("category2");
        category2.setCategoryId(2);
        category3 = new Category("accessories");
        category3.setCategoryId(3);

        product = new Product();
        product.setProductName("Test product");
        product.setDescription("Description of the test product");
        product.setPrice(1400);
        product.setCategory(category3);
        product.setQuantity(10);
        product.setRegions(Arrays.asList(Region.AU, Region.CA));
    }

    @Test
    public void addProductHandler_productExists_throwsRuntimeException(){

        Mockito.when(productRepo.findByProductName("Test product")).thenReturn(Optional.of(product));

        Assertions.assertThrows(RuntimeException.class, () -> addProductHandler.execute(product));
        Mockito.verify(productRepo, Mockito.times(0)).save(product);
    }

    @Test
    public void addProductHandler_productNameInvalid_throwsInvalidFieldException(){
        product.setProductName(" ");

        Mockito.when(productRepo.findByProductName("Test product")).thenReturn(Optional.of(product));

        Assertions.assertThrows(InvalidFieldException.class, () -> addProductHandler.execute(product));
        Mockito.verify(productRepo, Mockito.times(0)).save(product);
    }

    @Test
    public void addProductHandler_productPriceInvalid_throwsInvalidFieldException(){

        product.setPrice(-1400);

        Mockito.when(productRepo.findByProductName("Test product")).thenReturn(Optional.empty());
        Mockito.when(categoryRepo.findAll()).thenReturn(Arrays.asList(category1, category2, category3));

        Assertions.assertThrows(InvalidFieldException.class, () -> addProductHandler.execute(product));
        Mockito.verify(productRepo, Mockito.times(0)).save(product);
    }

    @Test
    public void addProductHandler_productRegionInvalid_throwsInvalidFieldException(){

        product.setRegions(Collections.emptyList());

        Mockito.when(productRepo.findByProductName("Test product")).thenReturn(Optional.empty());
        Mockito.when(categoryRepo.findAll()).thenReturn(Arrays.asList(category1, category2, category3));

        Assertions.assertThrows(InvalidFieldException.class, () -> addProductHandler.execute(product));
        Mockito.verify(productRepo, Mockito.times(0)).save(product);
    }

    @Test
    public void addProductHandler_productCategoryInvalid_throwsInvalidFieldException(){

        Category newCategory = new Category("invalid category");
        newCategory.setCategoryId(5);

        product.setCategory(newCategory);

        Mockito.when(productRepo.findByProductName("Test product")).thenReturn(Optional.empty());
        Mockito.when(categoryRepo.findAll()).thenReturn(Arrays.asList(category1, category2, category3));

        Assertions.assertThrows(InvalidFieldException.class, () -> addProductHandler.execute(product));
        Mockito.verify(productRepo, Mockito.times(0)).save(product);
    }

    @Test
    public void addProductHandler_saveProduct_returnsResponseEntityWithProductDTO(){

        Mockito.when(productRepo.findByProductName("Test product")).thenReturn(Optional.empty());
        Mockito.when(categoryRepo.findAll()).thenReturn(Arrays.asList(category1, category2, category3));

        ResponseEntity<ProductDTO> response = addProductHandler.execute(product);

        Assertions.assertEquals(HttpStatusCode.valueOf(201), response.getStatusCode());
        Assertions.assertEquals(product.getProductName(), response.getBody().getProductName());

        Mockito.verify(productRepo, Mockito.times(1)).save(product);
    }
}
