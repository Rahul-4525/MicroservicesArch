package com.microservices.PaymentProcessingService.dto;

import lombok.Data;



@Data
public class AccountData {
    private String accountNumber;
    private Double balance;
}
