package com.microservices.PaymentProcessingService.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("Gateway")
public interface OpenFeign {
    @PostMapping("api/account/modify/{customerId}/{balance}/{type}")
    ResponseEntity<String> modifyAccountBalance(@PathVariable String customerId, @PathVariable Double balance, @PathVariable String type);
    @PostMapping("api/notify/sendEmail/{email}/{msg}")
    ResponseEntity<String> notify(@PathVariable String email, @PathVariable String msg);
}
