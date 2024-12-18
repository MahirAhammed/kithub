package com.example.kithub.repository;

import com.example.kithub.category.Category;
import com.example.kithub.category.CategoryRepository;
import com.example.kithub.product.Product;
import com.example.kithub.product.ProductRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class ProductRepoTest {

    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private CategoryRepository categoryRepo;

    private Product product1, product2;

    @BeforeEach
    public void setup(){
        
        Category category = new Category("test category");
        categoryRepo.save(category);

       //List<Region> region1 = Arrays.asList(Region.CA, Region.AU, Region.ZA);
        //List<Region> region2 = Arrays.asList(Region.CA, Region.BD, Region.US);

        product1 = new Product();
        product1.setProductName("Test product");
        product1.setDescription("Description for the test product");
  //      product1.setRegions(region1);
        product1.setCategory(category);
        product1.setSupplier("Local store");

        product2 = new Product();
        product2.setProductName("Better product");
        product2.setDescription("Description for the better test product");
    //    product2.setRegions(region2);
        product2.setCategory(category);
        product2.setSupplier("Foreign store");

        productRepo.save(product1);
        productRepo.save(product2);
    }

    @Test
    @Order(1)
    public void saveProductTest(){
        System.out.println(product1);
        System.out.println(product2);
        Assertions.assertNotNull(product1.getProductId(), "Generated product ID is null");

        Optional<Product> currentProduct = productRepo.findById(product1.getProductId());
        Assertions.assertTrue(currentProduct.isPresent());

    }

    @Test
    @Order(2)
    public void getProductByNameTest(){
        Optional<Product> currentProduct = productRepo.findByProductName("Test product");
        Assertions.assertTrue(currentProduct.isPresent());
        Assertions.assertEquals("Test product", currentProduct.get().getProductName());
    }

    @Test
    @Order(3)
    public void getAllProductsWithNoFiltersTest(){
        List<Product> products = productRepo.findAll();
        Assertions.assertEquals(2, products.size());
    }

    @Test
    @Order(4)
    public void getProductByNameOrDescriptionContainingTest(){
        List<Product> products = productRepo.findProductsByMatchingCriteria("better test", null, null, null);
        Assertions.assertEquals(1, products.size());
        Assertions.assertEquals("Better product", products.get(0).getProductName());

    }

    @Test
    @Order(5)
    public void getProductsByCategoryContainingTest(){
        List<Product> products = productRepo.findProductsByMatchingCriteria(null, null, "Test category", null);
        Assertions.assertEquals(2, products.size());

    }

    @Test
    @Order(5)
    public void getProductsByCategoryTest_categoryNotFound(){
        List<Product> products = productRepo.findProductsByMatchingCriteria(null, null, "new category", null);
        Assertions.assertEquals(0, products.size());
    }

    @Test
    @Order(6)
    public void getProductBySupplierContainingTest(){
        List<Product> products = productRepo.findProductsByMatchingCriteria(null, null, null, "local");
        Assertions.assertEquals(1, products.size());
    }

    @Test
    @Order(6)
    public void getProductBySupplierContainingTest_supplierNotFound(){
        List<Product> products = productRepo.findProductsByMatchingCriteria(null, null, null, "adidas");
        Assertions.assertEquals(0, products.size());
    }

    /*@Test
    @Order(7)
    public void regionsTest(){
        List<Region> regions = Arrays.asList(Region.CA, Region.ZA, Region.AU);
        product1.setRegions(regions);

        List<Product> products = productRepo.findProductsByMatchingCriteria(null, "ZA", null, null);
        Assertions.assertEquals(1, products.size());
    }*/
}
