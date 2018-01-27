package com.company.core;


import com.company.model.Interfaces.RegisterRequestInt;
import com.company.model.User;
import com.company.model.dto.ErrorResponse;
import com.company.model.dto.UserRegistarionRequest;
import com.company.model.dto.UserRegistrationAndLoginResponce;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Data
public class RegisterRequest implements RegisterRequestInt {
    @Value("${urlRegister}")
    String url;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private User user;

    @SneakyThrows
    public boolean getResponce() {
        System.out.println("I'm here");
        setFields();
        boolean flag = false;
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<UserRegistrationAndLoginResponce> response = null;
            ObjectMapper mapper = new ObjectMapper();
            try {
                response = restTemplate.postForEntity(
                        url, new UserRegistarionRequest(firstName, lastName, email, password),
                        UserRegistrationAndLoginResponce.class);
                user = response.getBody().getUser();
                System.setProperty("SESSION_ID", response.getBody().getSessionId());
                System.setProperty("USER_ID", String.valueOf(user.getId()));
                System.out.println("User " + user.getFirstName() + " "
                        + user.getLastName() +
                        " was created " + "\nUser's id is " + user.getId() +
                        "\nSession ID is " + System.getProperty("SESSION_ID"));
                flag = true;
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
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @SneakyThrows
    private void setFields() {
        boolean flag = true;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input firstname (3-50 letters):");
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
        System.out.println("Input lastname (3-50 letters):");
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
        System.out.println("Input email (should match example@example.com):");
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
        flag = true;
        System.out.println("Input password (6-25 symbols):");
        while (flag) {
            password = bufferedReader.readLine();
            if (password.length() >= 6 && password.length() <= 20) {
                flag = false;
            } else if (password.length() == 0) {
                System.err.println("Password shouldn't be blank!");
            } else {
                System.err.println("Password should be 6 - 20 letters!");
            }
        }
    }

}

