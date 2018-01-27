package com.company.core;

import com.company.model.Interfaces.LoginRequestInt;
import com.company.model.User;
import com.company.model.dto.ErrorResponse;
import com.company.model.dto.UserLoginRequest;
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
public class LoginRequest implements LoginRequestInt {

    @Value("${urlLogin}")
    String url;
    private  String email = "";
    private  String password = "";
    public  User user;

    @SneakyThrows
    public boolean logIn() {
        ResponseEntity<UserRegistrationAndLoginResponce> response = null;
        boolean flag = false;
        setFields();

        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();

        try {
            response = restTemplate.postForEntity(url, new UserLoginRequest(email, password),
                    UserRegistrationAndLoginResponce.class);
            user = response.getBody().getUser();
            System.setProperty("SESSION_ID", response.getBody().getSessionId());
            System.setProperty("USER_ID", String.valueOf(user.getId()));
            System.out.println("User " + user.getFirstName() + " "
                    + user.getLastName() +
                    " is logged in.\nSession ID is" + System.getProperty("SESSION_ID"));
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

        return flag;
    }

    @SneakyThrows
    private  void setFields() {
        boolean flag = true;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
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
            } else {
                if (password.length() == 0) {
                    System.err.println("Password shouldn't be blank!");
                } else {
                    System.err.println("Password should be 6 - 20 letters!");
                }
            }
        }

    }


}