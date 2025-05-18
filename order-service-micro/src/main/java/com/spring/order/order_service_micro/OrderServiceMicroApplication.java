package com.spring.order.order_service_micro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderServiceMicroApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceMicroApplication.class, args);
	}

}
