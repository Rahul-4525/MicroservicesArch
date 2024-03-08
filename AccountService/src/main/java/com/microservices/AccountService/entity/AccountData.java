package com.microservices.AccountService.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("Accounts")
@Data
public class AccountData {
    @Id
    private String id;
    @Field("AccountNo")
    private String accountNumber;
    @Field("balance")
    private Double balance;
}
