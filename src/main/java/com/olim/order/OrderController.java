package com.olim.order;

import com.olim.product.ProductService;
import com.olim.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    ProductOrderService productOrderService;
    @Autowired
    OrderService orderService;
    @Autowired
    ProductService productService;

    @GetMapping("/checkout/{id}")
    public ResponseEntity<String> checkOutOrder(@PathVariable Long id){
        Order order = orderService.getOrder(id);
        List<ProductOrder> productOrders = order.getProductOrders();
        for(ProductOrder productOrder:productOrders) {
            productService.updateQuantity(productOrder.getProduct().getId(),productOrder.getQuantity());
        }
        if(orderService.checkOutOrder(order)){
            return ResponseEntity.ok("Product Checked out successfully");
        }
        return ResponseEntity.status(450).body("Check out failed");
    }
    @PostMapping("/save")
    public ResponseEntity<String> saveOrder(@RequestBody Order order){
        if(order == null){
            return ResponseEntity.badRequest().body("Order Must not be null");
        }
        List<ProductOrder> productOrders = order.getProductOrders();
        if(productOrders == null){
            return ResponseEntity.badRequest().body("Product orders Must not be null");
        }

        for(ProductOrder productOrder:productOrders){
            if(productService.productExist(productOrder.getProduct().getId())){
               productOrder.setProduct(productService.getProduct(productOrder.getProduct().getId()));
            }
            else {
                return ResponseEntity.status(410).body("Product "+productOrder.getProduct().getId()+" Does not exist");
            }
            System.out.println("product id:"+productOrder.getProduct().getId()+"product wanted: "+productOrder.getQuantity()+"while product available :"+productOrder.getProduct().getQuantity());
            if(productOrder.getQuantity() > productOrder.getProduct().getQuantity()){
                return ResponseEntity.status(410).body("Product "+productOrder.getProduct().getName()+" is  not above available");
            }
            productOrderService.saveProductOrder(productOrder);
        }

        if(orderService.saveOrder(order)){
            return ResponseEntity.ok("Order saved Successfully");
        }
        return ResponseEntity.ok("Order Not Saved");
    }
    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }
    @GetMapping("/all/user/{id}")
    public ResponseEntity<List<Order>> getAllUsersOrders(@PathVariable Long id){
        User user = new User();
        user.setId(id);
        return ResponseEntity.ok(orderService.getUserOrders(user));
    }

}
