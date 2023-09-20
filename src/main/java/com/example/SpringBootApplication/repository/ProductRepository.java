package com.example.SpringBootApplication.repository;

import com.example.SpringBootApplication.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Repository interface for managing Product entities.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}
