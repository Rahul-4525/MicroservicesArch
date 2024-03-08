package com.service.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("AuthService", r -> r.path("/api/auth/**")
						.uri("lb://AuthenticationService"))
				.route("PaymentInit", r -> r.path("/api/payments/**")
						.uri("lb://PaymentInitService"))
				.route("PaymentProcessing", r -> r.path("/api/process/**")
						.uri("lb://PaymentProcessing"))
				.route("AccountService", r -> r.path("/api/account/**")
						.uri("lb://AccountService"))
				.route("NotificationService", r -> r.path("/api/notify/**")
						.uri("lb://NotificationService"))
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

}
