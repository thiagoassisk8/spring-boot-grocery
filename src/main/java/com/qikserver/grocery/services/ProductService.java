package com.qikserver.grocery.services;

import com.qikserver.grocery.entities.CartItem;
import com.qikserver.grocery.entities.Order;
import com.qikserver.grocery.entities.Product;
import com.qikserver.grocery.entities.Cupom;
import com.qikserver.grocery.entities.CupomType;
import com.qikserver.grocery.client.ExternalApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private ExternalApi ExternalApi;

    @Autowired
    public ProductService(ExternalApi ExternalApi) {
        this.ExternalApi = ExternalApi;
    }

    public List<Product> fetchProducts() throws IOException {
        return this.ExternalApi.fetchProducts();
    }

    public Product fetchProductById(String productId) throws IOException {
        return this.ExternalApi.fetchProductById(productId);
    }

    public List<Order> addToCart(List<CartItem> items) throws IOException {
        return null;
    }

}
