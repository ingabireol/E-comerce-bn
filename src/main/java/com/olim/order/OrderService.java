package com.olim.order;

import com.olim.product.Product;
import com.olim.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public boolean saveOrder(Order order) {
        order = orderRepository.save(order);
        return orderRepository.findById(order.getId()).isPresent();
    }
    public boolean deleteOrder(Long id){
        orderRepository.deleteById(id);
        return !orderRepository.findById(id).isPresent();
    }
    public Order getOrder(Long id)  {
        return orderRepository.findById(id).get();
    }
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public boolean checkOutOrder(Order order) {
        order.setChecked(true);
        orderRepository.save(order);
        return orderRepository.existsById(order.getId());
    }
    public List<Order> getUserOrders(User user){
        return orderRepository.findAllByUser(user);
    }
}
