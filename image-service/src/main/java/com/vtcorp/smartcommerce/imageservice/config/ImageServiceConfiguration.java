package com.vtcorp.smartcommerce.imageservice.config;

import com.vtcorp.smartcommerce.imageservice.security.AuthenticationFilter;
import com.vtcorp.smartcommerce.imageservice.security.JwtConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ImageServiceConfiguration {
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Bean
    public AuthenticationFilter authenticationFilter() {
        return new AuthenticationFilter();
    }

    @Bean
    public JwtConfig jwtConfig() {
        return new JwtConfig();
    }
}
