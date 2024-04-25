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

    @CrossOrigin
    @GetMapping("/products")
    public ResponseEntity<Object> getAllProducts() throws IOException {
        List<Product> products = this.ProductService.fetchProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);

    }

    @CrossOrigin
    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable String productId) throws IOException {
        Product product = this.ProductService.fetchProductById(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/addCart")
    public ResponseEntity<List<Order>> addToCart(@RequestBody List<CartItem> items) throws IOException {
        for (CartItem item : items) {
            if (item.getQuantity() < 0) {
                String errorMessage = "Negative quantity is not allowed.";
                throw new IOException(errorMessage);
            }
            if (item.getQuantity() == 0) {
                String errorMessage = "No quantity associated with the product.";
                throw new IOException(errorMessage);
            }
        }

        List<Order> checkoutOrder = this.ProductService.addToCart(items);
        if (checkoutOrder.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(checkoutOrder);
    }

}
