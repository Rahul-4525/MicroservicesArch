package com.microservices.AccountService.controller;

import com.microservices.AccountService.entity.AccountData;
import com.microservices.AccountService.service.AccountService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("balance/{number}")
    @CircuitBreaker(name = "customerBalance", fallbackMethod = "getCustomerBalanceFallback")
    ResponseEntity<Double> getCustomerBalance(@PathVariable("number") String accountNumber) {
        return ResponseEntity.ok(accountService.getAccountBal(accountNumber));
    }

    @PostMapping("/modify/{customerId}/{balance}/{type}")
    @CircuitBreaker(name = "modifyBalance", fallbackMethod = "modifyAccountBalanceFallback")
    ResponseEntity<String> modifyAccountBalance(@PathVariable String customerId, @PathVariable Double balance,
            @PathVariable String type) {
        AccountData accountData = this.accountService.getAccount(customerId);
        var updatedBalance = switch (type) {
            case "reduce" -> accountData.getBalance() - balance;
            case "add" -> accountData.getBalance() + balance;
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
        accountData.setBalance(updatedBalance);
        accountService.updateAccountData(accountData);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/add")
    @CircuitBreaker(name = "addAccount", fallbackMethod = "addAccountFallback")
    ResponseEntity<String> addAccount(@RequestBody AccountData data) {
        accountService.updateAccountData(data);
        return ResponseEntity.accepted().build();
    }

    // circuit breakers
    ResponseEntity<Double> getCustomerBalanceFallback(Throwable ex) {
        return ResponseEntity.status(HttpStatusCode.valueOf(503)).body(null);
    }

    ResponseEntity<String> modifyAccountBalanceFallback(Throwable ex) {
        return ResponseEntity.status(HttpStatusCode.valueOf(503)).body(null);
    }

    ResponseEntity<String> addAccountFallback(Throwable ex) {
        return ResponseEntity.status(HttpStatusCode.valueOf(503)).body(null);
    }
}
