package com.vvt.icommerce.inventoryservice;

import com.vvt.icommerce.inventoryservice.messaging.OrderProcessor;
import com.vvt.icommerce.inventoryservice.model.Product;
import com.vvt.icommerce.inventoryservice.security.AuthenticationFilter;
import com.vvt.icommerce.inventoryservice.security.JwtConfig;
import com.vvt.icommerce.inventoryservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding({OrderProcessor.class})
public class InventoryServiceApplication {

	@Autowired
	private ProductService productService;

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

}
