package com.example.SpringBootApplication.service;


import com.example.SpringBootApplication.model.Product;
import com.example.SpringBootApplication.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class responsible for managing product-related operations.
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Retrieves a product by its unique identifier.
     */
    public Product findById(Long id) {
        return productRepository.getOne(id);
    }
    /**
     * Retrieves a list of all products.
     */
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    /**
     * Saves a product in the repository.
     */
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * Deletes a product by its unique identifier.
     */
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
