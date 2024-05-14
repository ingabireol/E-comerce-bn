package com.olim.product;

import com.olim.product.Product;
import com.olim.user.User;
import com.olim.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    public boolean saveProduct(Product product) {
        product = productRepository.save(product);
        return productRepository.findById(product.getId()).isPresent();
    }
    public boolean deleteProduct(Long id){

        productRepository.deleteById(id);
        return !productRepository.findById(id).isPresent();
    }
    public Product getProduct(Long id)  {
        return productRepository.findById(id).get();
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public List<Product> getAllUserProducts(Long id) {
        return productRepository.findAllByUser(userRepository.findById(id).get());
    }
    public boolean updateQuantity(Long id,int Quantity){
        return productRepository.updateProductQuantity(id,Quantity) > 0;
    }
    public boolean addProductQuantity(Long id,int Quantity){
        return productRepository.addProductQuantity(id,Quantity) > 0;
    }
    public boolean productExist(Long id){
        return productRepository.existsById(id);
    }
}
