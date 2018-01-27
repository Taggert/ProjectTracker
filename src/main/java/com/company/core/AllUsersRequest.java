package com.company.core;

import com.company.model.Interfaces.AllUsersRequestInt;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.company.model.User;
import com.company.model.dto.ErrorResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;
@Service
public class AllUsersRequest implements AllUsersRequestInt {

    @Value("${urlUsers}")
    String url;

    @SneakyThrows
    public ResponseEntity<User[]> getAllUsers() {
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
            response = restTemplate.exchange(url, HttpMethod.GET, entity,
                    User[].class);
        }catch (HttpClientErrorException e){
            ErrorResponse errorResponse = mapper.readValue(e.getResponseBodyAsString(), ErrorResponse.class);
            System.out.println( errorResponse.getMessage());
        }
        return response;
    }

    public void printResponse() {
        ResponseEntity<User[]> response = getAllUsers();

        System.out.println("Users in the base:\n");
        Arrays.asList(response.getBody()).forEach(System.out::println);
    }


}