package com.olim.order;

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
//            if(productOrder.getQuantity() > productOrder.getProduct().getQuantity()){
//                return ResponseEntity.status(410).body("Product "+productOrder.getProduct().getName()+" is  not above available");
//            }
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

}
