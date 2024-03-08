package com.microservices.AuthenticationService.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document("accounts")
public class Account {
    @Id
    private String Id;
    @Field("username")
    private String userName;
    @Field("password")
    private String password;
}
