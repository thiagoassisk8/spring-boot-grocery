package com.qikserver.grocery.controllers;

import com.qikserver.grocery.entities.CartItem;
import com.qikserver.grocery.entities.Order;
import com.qikserver.grocery.entities.Product;

import com.qikserver.grocery.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class ProductController {
    private final ProductService ProductService;

    @Autowired
    public ProductController(ProductService ProductService) {
        this.ProductService = ProductService;
    }

    @GetMapping("/products")
    public ResponseEntity<Object> getAllProducts() throws IOException {
        List<Product> products = this.ProductService.fetchProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);

    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable String productId) throws IOException {
        Product product = this.ProductService.fetchProductById(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/addCart")
    public ResponseEntity<List<Order>> addToCart(@RequestBody List<CartItem> items) throws IOException {
        List<Order> itemCheckouts = this.ProductService.addToCart(items);
        if (itemCheckouts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(itemCheckouts);
    }

    @GetMapping("/hello")
    public ResponseEntity<String> helloWorld() {
        return new ResponseEntity<>("Hello, World!", HttpStatus.OK);
    }
}
