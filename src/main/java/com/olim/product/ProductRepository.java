package com.olim.product;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Transactional
    @Modifying
    @Query("update Product p set p.quantity = p.quantity - :quantity where p.id = :productId")
    int updateProductQuantity(@Param("productId") Long productId, @Param("quantity") int quantity);


}
