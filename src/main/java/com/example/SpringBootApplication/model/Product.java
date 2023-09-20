package com.example.SpringBootApplication.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Table representing a product.
 */
@Entity
@Table(name = "product")
@Getter
@Setter
@ToString
public class Product {
    /**
     * Unique identifier of the product.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Type of the product.
     */
    @Column(name = "type")
    private String type;
    /**
     * Brand of the product.
     */
    @Column(name = "brand")
    private String brand;

    public Product(){}

    public Product(Long id, String type, String brand) {
        this.id = id;
        this.type = type;
        this.brand = brand;
    }
}
