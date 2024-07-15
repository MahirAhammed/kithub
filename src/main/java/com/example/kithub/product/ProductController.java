package com.example.kithub.product;

import com.example.kithub.product.commandhandlers.AddProductHandler;
import com.example.kithub.product.commandhandlers.DeleteProductHandler;
import com.example.kithub.product.queryhandlers.GetProductByIdHandler;
import com.example.kithub.product.queryhandlers.GetProductsHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final AddProductHandler addProductHandler;
    private final GetProductsHandler getProductsHandler;
    private final GetProductByIdHandler getProductByIdHandler;
    private final DeleteProductHandler deleteProductHandler;


    @Autowired
    public ProductController(AddProductHandler addProductHandler, GetProductsHandler getProductsHandler, GetProductByIdHandler getProductByIdHandler, DeleteProductHandler deleteProductHandler) {
        this.addProductHandler = addProductHandler;
        this.getProductsHandler = getProductsHandler;
        this.getProductByIdHandler = getProductByIdHandler;
        this.deleteProductHandler = deleteProductHandler;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProductsByCriteria(
            @RequestHeader(value = "Region", defaultValue = "ZA",required = false) String region,
            @RequestParam(required = false) String nameOrDescription,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false) String supplier
    ){
        return getProductsHandler.execute(new ProductRequest(region, nameOrDescription, category, orderBy, supplier));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable String id){
        return getProductByIdHandler.execute(id);

    }

    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(@RequestBody Product product){
        return addProductHandler.execute(product);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product product){
        product.setProductId(UUID.fromString(id));
        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable String id){
        return deleteProductHandler.execute(id);

    }

}
