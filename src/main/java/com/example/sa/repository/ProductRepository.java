package com.example.sa.repository;

import com.example.sa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.images")
    List<Product> findAllWithImages();
    @Query("SELECT pi.images FROM Product pi WHERE pi.id = :productId")
    List<String> findImagesByProductId(@Param("productId") Long productId);}
