package com.qikserver.grocery.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public ExternalApi ExternalApi() {
        return new ExternalApi();
    }
}
