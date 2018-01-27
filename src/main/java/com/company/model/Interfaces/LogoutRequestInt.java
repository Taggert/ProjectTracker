package com.company.model.Interfaces;

import org.springframework.http.ResponseEntity;

public interface LogoutRequestInt {

    public ResponseEntity<String> logOut();

    public  void printResponce();

}