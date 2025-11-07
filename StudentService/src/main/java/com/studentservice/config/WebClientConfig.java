package com.studentservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Value("${course.service.url}")
    private String courseServiceBaseUrl;

    @Bean
    public WebClient courseWebClient(WebClient.Builder builder) {
        return builder.baseUrl(courseServiceBaseUrl).build();
    }
}