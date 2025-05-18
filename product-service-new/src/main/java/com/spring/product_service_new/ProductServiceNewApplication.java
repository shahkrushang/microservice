package com.spring.product_service_new;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductServiceNewApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceNewApplication.class, args);
	}

}
