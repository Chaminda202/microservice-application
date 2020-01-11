package com.springboot.test.controller.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URI;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GreetingControllerJunitTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    int randomServerPort;
    @Test
    @Order(1)
    public void whenGenderMale() throws Exception {
        String name = "Tom";
        String gender = "Male";
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("http://localhost:");
        strBuilder.append(randomServerPort);
        strBuilder.append("/api/greetings");
        strBuilder.append("?name="+name);
        strBuilder.append("&gender="+gender);
        URI uri = new URI(strBuilder.toString());

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> request = new HttpEntity<>(null, headers);

        ResponseEntity<String> result = restTemplate.exchange(uri.toString(), HttpMethod.GET, request, String.class);
        Assertions.assertEquals(200, result.getStatusCodeValue());
        Assertions.assertEquals(String.format("Hello Mr. %s. How are you?", name), result.getBody());
    }

    @Test
    @Order(2)
    public void whenGenderFemale() throws Exception {
        String name = "Rehana";
        String gender = "Female";

        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("http://localhost:");
        strBuilder.append(randomServerPort);
        strBuilder.append("/api/greetings");
        strBuilder.append("?name="+name);
        strBuilder.append("&gender="+gender);
        URI uri = new URI(strBuilder.toString());

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> request = new HttpEntity<>(null, headers);

        ResponseEntity<String> result = restTemplate.exchange(uri.toString(), HttpMethod.GET, request, String.class);

        Assertions.assertEquals(200, result.getStatusCodeValue());
        Assertions.assertEquals(String.format("Hello Mrs. %s. How are you?", name), result.getBody());
    }
}