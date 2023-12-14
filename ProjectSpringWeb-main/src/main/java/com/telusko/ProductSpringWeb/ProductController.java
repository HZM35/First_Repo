package com.telusko.ProductSpringWeb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("productBackend")
public class ProductController {

    @Autowired
    ProductService service;

    // hey, this is for products request
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        return service.getAllProducts();
    }

    @GetMapping("/product/{name}")
    public ResponseEntity<Product> getProduct(@PathVariable String name){
        return service.getProduct(name);
    }

    @PostMapping("/product")
    public ResponseEntity<String> addProduct(@RequestBody Product p){
        return service.addProduct(p);
    }

    @DeleteMapping("/deleteProduct/{name}")
    public ResponseEntity<String> deleteProduct(@PathVariable String name){
        return service.deleteProduct(name);
    }
    @PutMapping("/updateProduct")
    public ResponseEntity<String> updateProduct(@RequestBody Product p){
        return service.updateProduct(p);
    }

}
