package com.microservices.AuthenticationService;

import com.microservices.AuthenticationService.controller.AuthenticationController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AuthenticationControllerTest {
    @Autowired
    private AuthenticationController controller;


    private TestRestTemplate restTemplate;

    @BeforeEach
    void contextLoads() {
        this.restTemplate = new TestRestTemplate();
        assertThat(controller).isNotNull();
    }

    @Test
    void addAccountTest() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username", "johndoe@gmail.com");
        map.add("password", "john123@");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, HttpHeaders.EMPTY);
        String addStatus = this.restTemplate.exchange("http://localhost:9000/addUser", HttpMethod.POST, request, String.class).getBody();
        assertThat(!addStatus.isEmpty());
    }

    @Test
    void authenticateUserAndValidateTokenTest() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username", "johndoe@gmail.com");
        map.add("password", "john123@");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, HttpHeaders.EMPTY);
        String token = this.restTemplate.exchange("http://localhost:9000/authenticate", HttpMethod.POST, request, String.class).getBody();
        assertThat(!token.isEmpty());
        assertThat(this.restTemplate.exchange("http://localhost:9000/validateToken/johndoe@gmail.com/" + token, HttpMethod.GET, null, Boolean.class).getBody().booleanValue() != false);

    }
}
