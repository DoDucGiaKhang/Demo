package com.vvt.icommerce.paymentservice.config;

import com.vvt.icommerce.paymentservice.security.AuthenticationFilter;
import com.vvt.icommerce.paymentservice.security.JwtConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class PaymentServiceConfiguration {
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
