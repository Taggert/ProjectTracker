package com.company.core;

import com.company.model.Interfaces.AllItemsRequestInt;
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

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
@Service
public class AllItemsRequest implements AllItemsRequestInt {

    @Value("${urlItems}")
    String url;

    @SneakyThrows
    public ResponseEntity<ItemInst[]> getAllItems(String flag){
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();

        ResponseEntity<ItemInst[]> response = null;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json;charset=UTF-8");
        headers.set("Authorization", System.getProperty("SESSION_ID"));
        HttpEntity entity = new HttpEntity(headers);
        Map<String, String> urlParams = new LinkedHashMap<>();
        urlParams.put("flag", flag);
        try{
            response = restTemplate.exchange(url, HttpMethod.GET, entity, ItemInst[].class, urlParams );
        } catch (HttpClientErrorException e){
            ErrorResponse errorResponse = mapper.readValue(e.getResponseBodyAsString(), ErrorResponse.class);
            System.out.println( errorResponse.getMessage());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    public  void printResponse(String flag){
        ResponseEntity<ItemInst[]> response = getAllItems(flag);
        System.out.println("Items:");
        Arrays.asList(response.getBody()).forEach(System.out::println);
    }


}