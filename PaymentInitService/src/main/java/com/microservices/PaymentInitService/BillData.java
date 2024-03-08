package com.microservices.PaymentInitService;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("Bills")
@Data
public class BillData {
    @Id
    private String Id;
    @Field("billNo")
    private String billNumber;
    @Field("amount")
    private Double billAmount;
    @Field("accountNo")
    private String accountNumber;
}
