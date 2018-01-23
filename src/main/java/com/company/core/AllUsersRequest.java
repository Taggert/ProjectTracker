package com.company.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.company.model.User;
import com.company.model.dto.ErrorResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

public class AllUsersRequest {
    @SneakyThrows
    private static ResponseEntity<User[]> getAllUsers() {
        RestTemplate restTemplate = new RestTemplate();
        Properties properties = new Properties();
        InputStream is = RegisterRequest.class.getResourceAsStream("/app.properties");
        try {
            properties.load(is);
        } catch (IOException e) {
            System.err.println("Something wrong with property file");
        }
        ObjectMapper mapper = new ObjectMapper();

        ResponseEntity<User[]> response = null;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", System.getProperty("SESSION_ID"));
        HttpEntity entity = new HttpEntity(headers);
        try {
            response = restTemplate.exchange(properties.getProperty("urlUsers"), HttpMethod.GET, entity,
                    User[].class);
        }catch (HttpClientErrorException e){
            ErrorResponse errorResponse = mapper.readValue(e.getResponseBodyAsString(), ErrorResponse.class);
            System.out.println( errorResponse.getMessage());
        }
        return response;
    }

    public static void printResponse() {
        ResponseEntity<User[]> response = getAllUsers();

        System.out.println("Users in the base:\n");
        Arrays.asList(response.getBody()).forEach(System.out::println);
    }


}