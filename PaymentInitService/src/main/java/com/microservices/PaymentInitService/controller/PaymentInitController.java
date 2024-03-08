package com.microservices.PaymentInitService.controller;

import com.microservices.PaymentInitService.BillData;
import com.microservices.PaymentInitService.client.OpenFeignClient;
import com.microservices.PaymentInitService.util.JwtUtil;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentInitController {
    @Autowired
    private OpenFeignClient feignClient;

    @GetMapping("/initiate/{token}")
    @CircuitBreaker(name = "initPayment", fallbackMethod = "handleInitPayment")
    public ResponseEntity<String> initPayment(@PathVariable String token, @RequestBody BillData bill) {
        try {
            //check validity  of token
            if (!feignClient.validateToken(token).getBody()) {
                return ResponseEntity.badRequest().body("Invalid Token");
            }
            //check account balance
            if (feignClient.getCustomerBalance(bill.getAccountNumber()).getBody() < bill.getBillAmount()) {
                feignClient.notify(JwtUtil.extractUsername(token),"Insufficient balance in your account!");
                return ResponseEntity.badRequest().body("Insufficient Balance");
            }
            //call payment service
            feignClient.processPayment(token, bill);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Unable to process the request now\n" + ex.getMessage());
        }
        return ResponseEntity.ok().body("Payment Success");
    }

    public ResponseEntity<String> handleInitPayment(Exception ex) {
        return ResponseEntity.internalServerError().body("Currently experiencing high number of requests, please try after some time");
    }
}
