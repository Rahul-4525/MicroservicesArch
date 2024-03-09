package com.microservices.NotificationService.controller;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class NotificationController {
//    @Autowired
//    private JavaMailSender emailSender;

    @PostMapping("sendEmail/{email}/{msg}")
    @CircuitBreaker(name = "notify", fallbackMethod = "notifyFallback")
    public ResponseEntity<String> notify(@PathVariable String email, @PathVariable String msg) {
        return ResponseEntity.ok("Notified the recipient.");
    }

//    public void sendEmail(
//            String to, String subject, String text) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("notifyservice@gmail.com");
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(text);
//        emailSender.send(message);
//    }
    //circuit breaker
    public ResponseEntity<String> notifyFallback(Throwable ex) {
        return ResponseEntity.status(HttpStatusCode.valueOf(503)).build();
    }

}
