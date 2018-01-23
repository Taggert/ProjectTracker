package com.company.core;

import com.company.model.ItemInst;
import com.company.model.dto.ErrorResponse;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class ItemStatusChangeRequest {

    private static Integer itemId;

    @SneakyThrows
    public static void changeStatus() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ItemInst> response = null;
        Properties properties = new Properties();
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = RegisterRequest.class.getResourceAsStream("/app.properties");
        try {
            properties.load(is);
        } catch (IOException e) {
            System.err.println("Something wrong with property file");
        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input item id to change status (chek using 'Show all items'):");
        itemId = Integer.parseInt(bufferedReader.readLine());
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Authorization", System.getProperty("SESSION_ID"));
        Map<String, String> urlParams = new LinkedHashMap<>();
        urlParams.put("itemID", String.valueOf(itemId));
        HttpEntity entity = new HttpEntity(headers);
        try {
            response = restTemplate.exchange(properties.getProperty("urlItem"), HttpMethod.PUT, entity, ItemInst.class, urlParams);
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

    }


}