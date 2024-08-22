package com.example.sa;

import org.springframework.data.jpa.repository.JpaRepository;

// ProductRepository.java
public interface ProductRepository extends JpaRepository<Product, Long> {
}
