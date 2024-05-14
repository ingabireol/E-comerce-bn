package com.olim.product;

import com.olim.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private Double price;
    private int quantity;
    private boolean available;
    @ManyToOne
    private User user;

}
