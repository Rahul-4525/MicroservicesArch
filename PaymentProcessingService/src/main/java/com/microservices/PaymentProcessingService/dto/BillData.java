package com.microservices.PaymentProcessingService.dto;


import lombok.Data;

@Data
public class BillData {

    private String billNumber;
    private Double billAmount;
    private String accountNumber;
}
