package com.company.core;

import com.company.model.Interfaces.UpdateUserByIdInt;
import com.company.model.User;
import com.company.model.dto.ErrorResponse;
import com.company.model.dto.UserUpdateRequest;
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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Service
public class UpdateUserById implements UpdateUserByIdInt {


    private String firstName;
    private String lastName;
    private String email;

    @Value("${urlUser}")
    String url;

    @SneakyThrows
    public ResponseEntity<User> updateUserById() {


        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User> response = null;
        ObjectMapper mapper = new ObjectMapper();

        setFields();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Authorization", System.getProperty("SESSION_ID"));
        HttpEntity entityCheck = new HttpEntity(headers);
        Map<String, String> urlParams = new LinkedHashMap<>();

        urlParams.put("userID", System.getProperty("USER_ID"));

        HttpEntity<UserUpdateRequest> entity = new HttpEntity(new UserUpdateRequest(firstName, lastName, email), headers);
        try {
            response = restTemplate.exchange(url, HttpMethod.PUT,
                    entity, User.class, urlParams);
            System.out.println(response.getBody());
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


    public  void printResponce() {

        ResponseEntity<User> response = updateUserById();
        System.out.println(response.getBody());


    }

    @SneakyThrows
    private void setFields() {
        boolean flag = true;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input new firstname (3-50 letters):");
        while (flag) {
            firstName = bufferedReader.readLine();
            if (firstName.length() >= 3 && firstName.length() <= 50) {
                flag = false;
            } else {
                if (firstName.length() == 0) {
                    System.err.println("Firstname shouldn't be blank!");
                } else {
                    System.err.println("Firstname should be 3 - 50 letters!");
                }
            }
        }
        flag = true;
        System.out.println("Input new lastname (3-50 letters):");
        while (flag) {
            lastName = bufferedReader.readLine();
            if (lastName.length() >= 3 && lastName.length() <= 50) {
                flag = false;
            } else {
                if (lastName.length() == 0) {
                    System.err.println("Lastname shouldn't be blank!");
                } else {
                    System.err.println("Lastname should be 3 - 50 letters!");
                }
            }
        }
        flag = true;
        System.out.println("Input new email (should match example@example.com):");
        while (flag) {
            email = bufferedReader.readLine();
            Pattern p = Pattern.compile("\\w+\\@[0-9a-zA-Z]+\\.[a-zA-Z]+");
            Matcher m = p.matcher(email);
            if (m.matches()) {
                flag = false;
            } else {
                if (email.length() == 0) {
                    System.err.println("Email shouldn't be blank!");
                } else {
                    System.err.println("Email should match example!");
                }
            }
        }

    }


}