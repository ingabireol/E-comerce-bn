package com.olim.product;

import com.olim.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/save")
    public ResponseEntity<String> saveProduct(@RequestBody Product product){
        if(service.saveProduct(product)){
            return ResponseEntity.ok("Product saved successfully");
        }
        else return ResponseEntity.status(303).body("Product not saved");
    }
    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.status(200).body(service.getAllProducts());
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id){
        return ResponseEntity.status(200).body(service.getProduct(id));
    }
}
