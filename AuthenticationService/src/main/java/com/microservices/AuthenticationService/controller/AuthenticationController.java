package com.microservices.AuthenticationService.controller;

import com.microservices.AuthenticationService.entity.Account;
import com.microservices.AuthenticationService.service.AccountService;
import com.microservices.AuthenticationService.util.JwtUtil;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {
    @Autowired
    private AccountService accountService;

    @SuppressWarnings("rawtypes")
    @PostMapping("addUser")
    @CircuitBreaker(name = "addAccount", fallbackMethod = "addAccountFallback")
    public ResponseEntity addAccount(@RequestBody Account account) {
        this.accountService.addAccount(account);
        return ResponseEntity.accepted().body(account.getUserName());
    }

    @PostMapping("authenticate")
    @CircuitBreaker(name = "authenticateUser", fallbackMethod = "authenticateUserFallback")
    public ResponseEntity<String> authenticateUser(@RequestBody Account account) {
        Account accountFromDB = this.accountService.getAccountDetails(account.getUserName());
        boolean authSuccess = this.accountService.verifyAccount(account, accountFromDB.getPassword());
        if (!authSuccess)
            return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok(JwtUtil.generateToken(account.getUserName()));
    }

    @GetMapping("validateToken/{token}")
    @CircuitBreaker(name = "validateToken", fallbackMethod = "validateTokenFallback")
    public ResponseEntity<Boolean> validateToken(@PathVariable("token") String token) {
        if (!JwtUtil.extractUsername(token).isEmpty()) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.badRequest().body(false);
    }

    // circuit breakers
    @SuppressWarnings("rawtypes")
    public ResponseEntity addAccountFallback(Throwable ex) {
        return ResponseEntity.status(HttpStatusCode.valueOf(503)).build();
    }

    public ResponseEntity<String> authenticateUserFallback(Throwable ex) {
        return ResponseEntity.status(HttpStatusCode.valueOf(503)).build();
    }

    public ResponseEntity<Boolean> validateTokenFallback(Throwable ex) {
        return ResponseEntity.status(HttpStatusCode.valueOf(503)).build();
    }

}
