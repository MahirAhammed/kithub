package com.example.kithub.product;

import com.example.kithub.product.commandhandlers.AddProductHandler;
import com.example.kithub.product.commandhandlers.DeleteProductHandler;
import com.example.kithub.product.commandhandlers.InventoryHandler;
import com.example.kithub.product.commandhandlers.UpdateProductHandler;
import com.example.kithub.product.queryhandlers.GetProductByIdHandler;
import com.example.kithub.product.queryhandlers.GetProductsHandler;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@EnableMethodSecurity
public class ProductController {

    private final AddProductHandler addProductHandler;
    private final GetProductsHandler getProductsHandler;
    private final GetProductByIdHandler getProductByIdHandler;
    private final UpdateProductHandler updateProductHandler;
    private final DeleteProductHandler deleteProductHandler;
    private final InventoryHandler inventoryHandler;


    @Autowired
    public ProductController(AddProductHandler addProductHandler, GetProductsHandler getProductsHandler, GetProductByIdHandler getProductByIdHandler, UpdateProductHandler updateProductHandler, DeleteProductHandler deleteProductHandler, InventoryHandler inventoryHandler) {
        this.addProductHandler = addProductHandler;
        this.getProductsHandler = getProductsHandler;
        this.getProductByIdHandler = getProductByIdHandler;
        this.updateProductHandler = updateProductHandler;
        this.deleteProductHandler = deleteProductHandler;
        this.inventoryHandler = inventoryHandler;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProductsByCriteria(
            @RequestHeader(value = "Region", defaultValue = "ZA", required = false) String region,
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody Product product){
        return addProductHandler.execute(product);

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product product){
        return updateProductHandler.execute(new ProductRequest(id, product));

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteProduct(@PathVariable String id){
        return deleteProductHandler.execute(id);

    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductDTO> incrementProductQuantity(
            @PathVariable("id") String id,
            @RequestParam(name = "increase", defaultValue = "1") int quantity
    ){
        return inventoryHandler.incrementProduct(id, quantity);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductDTO> decrementProductQuantity(
            @PathVariable("id") String id,
            @RequestParam(name = "decrease", defaultValue = "1") int quantity)
    {
        return inventoryHandler.decrementProduct(id, quantity);
    }

}
