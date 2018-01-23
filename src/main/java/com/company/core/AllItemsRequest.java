package com.company.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.company.model.ItemInst;
import com.company.model.dto.ErrorResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

public class AllItemsRequest {
    @SneakyThrows
    private static ResponseEntity<ItemInst[]> getAllItems(){
        RestTemplate restTemplate = new RestTemplate();
        Properties properties = new Properties();
        InputStream is = AllItemsRequest.class.getResourceAsStream("/app.properties");
        try {
            properties.load(is);
        } catch (IOException e) {
            System.err.println("Something wrong with property file");
        }
        ObjectMapper mapper = new ObjectMapper();

        ResponseEntity<ItemInst[]> response = null;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json;charset=UTF-8");
        headers.set("Authorization", System.getProperty("SESSION_ID"));
        HttpEntity entity = new HttpEntity(headers);
        try{
            response = restTemplate.exchange(properties.getProperty("urlItems"), HttpMethod.GET, entity, ItemInst[].class );
        } catch (HttpClientErrorException e){
            ErrorResponse errorResponse = mapper.readValue(e.getResponseBodyAsString(), ErrorResponse.class);
            System.out.println( errorResponse.getMessage());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    public static void printResponse(){
        ResponseEntity<ItemInst[]> response = getAllItems();
        System.out.println("Items:");
        Arrays.asList(response.getBody()).forEach(System.out::println);
    }


}