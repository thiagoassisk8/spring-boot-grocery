package com.qikserver.grocery.services;

import com.qikserver.grocery.entities.Product;
import com.qikserver.grocery.client.ExternalApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

}
