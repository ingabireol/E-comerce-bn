package com.olim.order;

import com.olim.product.Product;
import com.olim.user.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Product product;
    private int quantity;
}
