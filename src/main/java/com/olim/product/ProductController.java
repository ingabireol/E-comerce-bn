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
    @GetMapping("/get/user/{id}")
    public ResponseEntity<List<Product>> getUserProducts(@PathVariable Long id){
        return ResponseEntity.status(200).body(service.getAllUserProducts(id));
    }
    @GetMapping("/get/all")
    public ResponseEntity<List<Product>> getAll(){
        return ResponseEntity.status(200).body(service.getAllProducts());
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id){
        return ResponseEntity.status(200).body(service.getProduct(id));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        if (!service.productExist(id))
            return ResponseEntity.ok("Product does not exist");

        return service.deleteProduct(id)? ResponseEntity.ok("Product Delete"):
                ResponseEntity.status(210).body("Product not deleted");
    }

}
