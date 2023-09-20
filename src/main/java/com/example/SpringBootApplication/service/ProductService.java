package com.example.SpringBootApplication.service;


import com.example.SpringBootApplication.model.Product;
import com.example.SpringBootApplication.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class responsible for managing product-related operations.
 */
@Service
public class ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Retrieves a product by its unique identifier.
     */
    public Product findById(Long id) {
        logger.info("Finding product by ID: {}", id);
        return productRepository.getOne(id);
    }
    /**
     * Retrieves a list of all products.
     */
    public List<Product> findAll() {
        logger.info("Retrieving all products");
        return productRepository.findAll();
    }

    /**
     * Saves a product in the repository.
     */
    public Product saveProduct(Product product) {
        logger.info("Saving product: {}", product);
        return productRepository.save(product);
    }

    /**
     * Deletes a product by its unique identifier.
     */
    public void deleteById(Long id) {
        logger.info("Deleting product by ID: {}", id);
        productRepository.deleteById(id);
    }
}
