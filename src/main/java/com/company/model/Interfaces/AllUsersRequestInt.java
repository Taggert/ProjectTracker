package com.company.model.Interfaces;

import com.company.model.User;
import org.springframework.http.ResponseEntity;

public interface AllUsersRequestInt {

    public ResponseEntity<User[]> getAllUsers();
    public  void printResponse();


}