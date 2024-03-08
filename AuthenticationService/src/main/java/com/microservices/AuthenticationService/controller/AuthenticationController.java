package com.microservices.AuthenticationService.controller;

import com.microservices.AuthenticationService.entity.Account;
import com.microservices.AuthenticationService.service.AccountService;
import com.microservices.AuthenticationService.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {
    @Autowired
    private AccountService accountService;
    @PostMapping("addUser")
    public ResponseEntity addAccount(@RequestBody Account account)
    {
        this.accountService.addAccount(account);
        return ResponseEntity.accepted().body(account.getUserName());
    }
    @PostMapping("authenticate")
    public ResponseEntity<String> authenticateUser(@RequestBody Account account)
    {
        Account accountFromDB = this.accountService.getAccountDetails(account.getUserName());
        boolean authSuccess = this.accountService.verifyAccount(account,accountFromDB.getPassword());
        if(!authSuccess) return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok(JwtUtil.generateToken(account.getUserName()));
    }
    @GetMapping("validateToken/{token}")
    public ResponseEntity<Boolean> validateToken(@PathVariable("token")String token){
        if (!JwtUtil.extractUsername(token).isEmpty()) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.badRequest().body(false);
    }
}
