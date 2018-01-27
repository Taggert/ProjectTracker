package com.company.core;

import com.company.model.Interfaces.DeleteItemRequestInt;
import com.company.model.ItemInst;
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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
@Service
public class DeleteItemRequest implements DeleteItemRequestInt {
    @Value("${urlDelete}")
    String url;
    private static Integer itemId;

    @SneakyThrows
    public void deleteItem() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ItemInst> response = null;
        ObjectMapper mapper = new ObjectMapper();


        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input item id to delete (chek using 'Show all items'):");
        itemId = Integer.parseInt(bufferedReader.readLine());
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Authorization", System.getProperty("SESSION_ID"));
        Map<String, String> urlParams = new LinkedHashMap<>();
        urlParams.put("itemID", String.valueOf(itemId));
        HttpEntity entity = new HttpEntity(headers);
        try {
            response = restTemplate.exchange(url, HttpMethod.DELETE, entity, ItemInst.class, urlParams);
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