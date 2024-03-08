package com.microservices.PaymentInitService.client;

import com.microservices.PaymentInitService.BillData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "Gateway")
public interface OpenFeignClient {

    @GetMapping("api/auth/validateToken/{token}")
    ResponseEntity<Boolean> validateToken(@PathVariable("token") String token);

    @GetMapping("api/account/balance/{number}")
    ResponseEntity<Double> getCustomerBalance(@PathVariable("number") String accountNumber);

    @PostMapping("api/process/payment/{token}")
    ResponseEntity<String> processPayment(@PathVariable("token") String token, @RequestBody BillData bill);

    @PostMapping("api/notify/sendEmail/{email}/{msg}")
    ResponseEntity<String> notify(@PathVariable String email, @PathVariable String msg);


}
