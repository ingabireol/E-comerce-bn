package com.olim.order;

import com.olim.product.Product;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductOrderRepo extends JpaRepository<ProductOrder,Long> {

}
