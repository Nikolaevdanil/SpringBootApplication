package com.example.SpringBootApplication.controller;


import com.example.SpringBootApplication.model.Product;
import com.example.SpringBootApplication.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing CRUD (Create, Read, Update, Delete) operations on products.
 */
@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Displays the list of products.
     */
    @GetMapping
    public String listProducts(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "list";
    }

    /**
     * Displays a form for adding a new product.
     */
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        return "add";
    }

    /**
     * Handles the request to add a new product.
     */
    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/products";
    }

    /**
     * Displays a form for editing an existing product.
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product product = productService.findById(id);
        if (product != null) {
            model.addAttribute("product", product);
            return "edit";
        } else {
            return "redirect:/products";
        }
    }

    /**
     * Handles the request to edit an existing product.
     */

    @PostMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id, @ModelAttribute Product updatedProduct) {
        Product product = productService.findById(id);
        if (product != null) {
            product.setType(updatedProduct.getType());
            product.setBrand(updatedProduct.getBrand());
            productService.saveProduct(product);
        }
        return "redirect:/products";
    }

    /**
     * Handles the request to delete a product.
     */
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/products";
    }
}