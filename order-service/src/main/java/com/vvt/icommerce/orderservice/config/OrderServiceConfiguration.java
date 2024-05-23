package com.vvt.icommerce.orderservice.config;

import com.vvt.icommerce.orderservice.model.Order;
import com.vvt.icommerce.orderservice.model.PaymentOrder;
import com.vvt.icommerce.orderservice.security.AuthenticationFilter;
import com.vvt.icommerce.orderservice.security.JwtConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class OrderServiceConfiguration {
    @Bean
    public Map<Long, PaymentOrder> paymentOrders() {
        Map<Long, PaymentOrder> paymentOrders = new HashMap<Long, PaymentOrder>();
        return paymentOrders;
    }

    @Bean
    public Map<Long, Order> productOrders() {
        Map<Long, Order> productOrders = new HashMap<Long, Order>();
        return productOrders;
    }

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
