package com.example.kithub.service;

import com.example.kithub.category.Category;
import com.example.kithub.product.Product;
import com.example.kithub.product.ProductDTO;
import com.example.kithub.product.ProductRepository;
import com.example.kithub.product.ProductRequest;
import com.example.kithub.product.queryhandlers.GetProductsHandler;
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
import java.util.List;


public class GetProductsByCriteriaTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private GetProductsHandler getProductsHandler;

    private Product product1, product2, product3;


    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);

        product1 = new Product();
        product1.setProductName("Test product 1");
        product1.setDescription("Description for the test product");
        product1.setSupplier("kithub");
        product1.setCategory(new Category("test category"));
        product1.setPrice(1000.00);

        product2 = new Product();
        product2.setProductName("Better test product");
        product2.setDescription("Description for the better test product");
        product2.setSupplier("kithub");
        product2.setCategory(new Category("test category"));
        product2.setPrice(1300.00);

        product3 = new Product();
        product3.setProductName("Worst test product");
        product3.setDescription("Description for the least favourite test product");
        product3.setSupplier("kithub");
        product3.setCategory(new Category("test category"));
        product3.setPrice(300.00);
    }

    @Test
    public void getProductsByCriteria_service_returnsProductListUnsorted(){
        ProductRequest request = new ProductRequest("region1", "nameAndDescription", "test category", "none", "default");

        // given
        List<Product> products = Arrays.asList(product1, product2, product3);
        Mockito.when(repository.findProductsByMatchingCriteria("nameAndDescription", "region1", "test category","default"))
                .thenReturn(products);

        // when
        ResponseEntity<List<ProductDTO>> response = getProductsHandler.execute(request);

        // then
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        Assertions.assertEquals(3, response.getBody().size());

       // Mockito.verify(repository, Mockito.times(1)).findProductsByMatchingCriteria();
    }

    @Test
    public void getProductsByCriteria_service_returnsEmptyList(){
        ProductRequest request = new ProductRequest("region1", "nameAndDescription", "test category", "none", "default");

        // given
        Mockito.when(repository.findProductsByMatchingCriteria("nameAndDescription", "region1", "test category","default"))
                .thenReturn(Collections.emptyList());

        // when
        ResponseEntity<List<ProductDTO>> response = getProductsHandler.execute(request);

        // then
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        Assertions.assertEquals(0, response.getBody().size());

    }

    @Test
    public void getProductsByCriteria_service_returnsListSortedByName(){
        ProductRequest request = new ProductRequest("region1", "nameAndDescription", "test category", "name", "default");

        // given
        List<Product> products = Arrays.asList(product1, product2, product3);
        Mockito.when(repository.findProductsByMatchingCriteria("nameAndDescription", "region1", "test category","default"))
                .thenReturn(products);

        // when
        ResponseEntity<List<ProductDTO>> response = getProductsHandler.execute(request);
        List<ProductDTO> sortedProducts = response.getBody();

        // then
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        Assertions.assertEquals(3, response.getBody().size());

        Assertions.assertEquals(sortedProducts.get(0).getProductName(), product2.getProductName());
        Assertions.assertEquals(sortedProducts.get(1).getProductName(), product1.getProductName());
        Assertions.assertEquals(sortedProducts.get(2).getProductName(), product3.getProductName());
    }

    @Test
    public void getProductsByCriteria_service_returnsListSortedByPrice(){
        ProductRequest request = new ProductRequest("region1", "nameAndDescription", "test category", "price", "default");

        // given
        List<Product> products = Arrays.asList(product1, product2, product3);
        Mockito.when(repository.findProductsByMatchingCriteria("nameAndDescription", "region1", "test category","default"))
                .thenReturn(products);

        // when
        ResponseEntity<List<ProductDTO>> response = getProductsHandler.execute(request);
        List<ProductDTO> sortedProducts = response.getBody();

        // then
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        Assertions.assertEquals(3, response.getBody().size());

        Assertions.assertEquals(sortedProducts.get(0).getProductName(), product3.getProductName());
        Assertions.assertEquals(sortedProducts.get(1).getProductName(), product1.getProductName());
        Assertions.assertEquals(sortedProducts.get(2).getProductName(), product2.getProductName());
    }
}
