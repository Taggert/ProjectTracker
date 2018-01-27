package com.company.core;

import com.company.model.Interfaces.LogoutRequestInt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class LogoutRequest implements LogoutRequestInt {

    @Value("${urlLogout}")
    String url;

    public ResponseEntity<String> logOut() {
        RestTemplate restTemplate = new RestTemplate();


        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Authorization", System.getProperty("SESSION_ID"));
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST,
                entity, String.class);
        System.clearProperty(System.getProperty("SESSION_ID"));
        return response;
    }

    public void printResponce() {
        ResponseEntity<String> response = logOut();
        System.out.println("User is logged out");

    }

}