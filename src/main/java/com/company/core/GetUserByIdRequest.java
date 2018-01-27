package com.company.core;

import com.company.model.Interfaces.GetUserByIdRequestInt;
import com.company.model.User;
import com.company.model.dto.ErrorResponse;
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

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class GetUserByIdRequest implements GetUserByIdRequestInt {

    @Value("${urlUser}")
    String url;

    @SneakyThrows
    public ResponseEntity<User> getUserById() {


        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        ResponseEntity<User> response = null;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Authorization", System.getProperty("SESSION_ID"));
        HttpEntity entity = new HttpEntity(headers);

        Map<String, String> urlParams = new LinkedHashMap<>();
        urlParams.put("userID", System.getProperty("USER_ID"));
        try {
            response = restTemplate.exchange(url, HttpMethod.GET,
                    entity, User.class, urlParams);
        } catch (HttpClientErrorException e) {
            ErrorResponse errorResponse = mapper.readValue(e.getResponseBodyAsString(), ErrorResponse.class);
            System.out.println("Unable to continue because of:");
            if (errorResponse.getErrorResponse() != null) {
                for (List errors : errorResponse.getErrorResponse().values()) {
                    System.out.println(errors);
                }
            } else {
                System.out.println(errorResponse.getMessage());
            }
        }
        return response;
    }

    public void printResponce() {
        ResponseEntity<User> response = getUserById();
        if(response.getBody() == null){
            System.out.println("No such user");
        }else {
            System.out.println(getUserById().getBody());
        }
    }



}