package com.company.core;

import com.company.model.ItemInst;
import com.company.model.dto.ErrorResponse;
import com.company.model.dto.ItemCreate;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

public class CreateItemRequest {

    private static String title;
    private static String description;
    private static Integer itemType;

    @SneakyThrows
    public static boolean getResponce() {
        boolean flag = false;
        setFields();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ItemInst> response = null;
        ObjectMapper mapper = new ObjectMapper();
        Properties properties = new Properties();
        InputStream is = RegisterRequest.class.getResourceAsStream("/app.properties");
        try {
            properties.load(is);
        } catch (IOException e) {
            System.err.println("Something wrong with property file");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Authorization", System.getProperty("SESSION_ID"));
        HttpEntity<ItemCreate> entity = new HttpEntity(new ItemCreate(title, description, itemType), headers);
        try {
            response = restTemplate.exchange(properties.getProperty("urlItems"), HttpMethod.POST, entity, ItemInst.class );
            System.out.println(response.getBody());
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
    private static void setFields() {
        boolean flag = true;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input title (3-50 letters):");
        while (flag) {
            title = bufferedReader.readLine();
            if (title.length() >= 5 && title.length() <= 50) {
                flag = false;
            } else {
                if (title.length() == 0) {
                    System.err.println("Title shouldn't be blank!");
                } else {
                    System.err.println("Title should be 5 - 50 letters!");
                }
            }
        }
        flag = true;
        System.out.println("Input description (3-50 letters):");
        while (flag) {
            description = bufferedReader.readLine();
            if (description.length() != 0) {
                flag = false;
            } else {
                System.err.println("Description shouldn't be blank!");

            }
        }
        flag = true;
        System.out.println("Input type of item (1 - Task, 2 - Bug, 3 - Test):");
        while (flag) {
            itemType = Integer.parseInt(bufferedReader.readLine());
            if (itemType <= 3 && itemType >= 1) {
                flag = false;
            } else {

                System.err.println("Item type id must be between 1 and 3");

            }
        }

    }


}