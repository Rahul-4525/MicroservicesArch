package com.microservices.PaymentProcessingService.controller;

import com.microservices.PaymentProcessingService.dto.BillData;
import com.microservices.PaymentProcessingService.service.OpenFeign;
import com.microservices.PaymentProcessingService.util.JwtUtil;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProcessingController {
    //receive token and bill data from payment init service
    //get email from token
    //using account number and reduce the amount by bill amount [account service]
    //once both  are successful
    @Autowired
    private OpenFeign openFeign;
    private static final String merchantAccount = "35625";
    @PostMapping("payment/{token}")
    @CircuitBreaker(name = "payment",fallbackMethod = "processPaymentFallback")
    ResponseEntity<String> processPayment(@PathVariable("token") String token, @RequestBody BillData bill){
        String userEmail = JwtUtil.extractUsername(token);
        String accountNumber = bill.getAccountNumber();
        try{
            //payment gateway stub
            openFeign.modifyAccountBalance(accountNumber,bill.getBillAmount(),"reduce");
            openFeign.modifyAccountBalance(merchantAccount, bill.getBillAmount(),"add");
            openFeign.notify(userEmail,"Transaction success.");
        }catch (Exception ex)
        {
            openFeign.notify(userEmail,"Unable to process the payment now.");
         return ResponseEntity.internalServerError().body(ex.getMessage());
        }
        return ResponseEntity.ok().body("Payment is processed.");
    }
    //circuit breaker
    ResponseEntity<String> processPaymentFallback(Throwable ex)
    {
        return ResponseEntity.internalServerError().body("Unable to process the payments now");
    }
}
