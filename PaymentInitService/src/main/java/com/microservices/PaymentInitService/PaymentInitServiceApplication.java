package com.microservices.PaymentInitService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class PaymentInitServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentInitServiceApplication.class, args);
	}

}
