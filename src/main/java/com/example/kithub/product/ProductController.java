package com.example.kithub.product;

import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @GetMapping
    public ResponseEntity<ProductDTO> getProductsByCriteria(
            @RequestHeader(value = "Region", defaultValue = "ZA") String region,
            @RequestParam(required = false) String nameOrDescription,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false) String supplier
    ){
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable String id){
        return ResponseEntity.ok().build();

    }

    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(
            @RequestBody ProductDTO product,
            @RequestHeader(value = "Region", defaultValue = "ZA") String region){

        return ResponseEntity.ok().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable String id, @RequestBody ProductDTO product){
        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable String id){
        return ResponseEntity.ok().build();

    }

}
