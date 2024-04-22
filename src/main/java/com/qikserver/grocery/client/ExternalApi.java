package com.qikserver.grocery.client;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qikserver.grocery.entities.Product;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "api")

public class ExternalApi {

    private final OkHttpClient client;
    private final Gson gson;
    private String endpoint;

    public String getEndpoint() {
        return endpoint;
    };

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    };

    public ExternalApi() {
        client = new OkHttpClient();
        gson = new Gson();
    }

    public List<Product> fetchProducts() throws IOException {
        String url = getEndpoint() + "/products";
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                String errorMessage = "There's no products: " + response;
                throw new IOException(errorMessage);
            }

            return parseResponse(response, new TypeToken<List<Product>>() {
            }.getType());
        }
    }

    public Product fetchProductById(String productId) throws IOException {
        String url = getEndpoint() + "/products/" + productId;
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                String errorMessage = String.format("There's no product with ID %s: %s", productId, response);
                throw new IOException(errorMessage);
            }

            return parseResponse(response, Product.class);
        }
    }

    private <T> T parseResponse(Response response, Type type) throws IOException {
        return gson.fromJson(response.body().string(), type);
    }
}