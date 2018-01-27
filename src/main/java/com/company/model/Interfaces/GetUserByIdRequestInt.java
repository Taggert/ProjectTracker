package com.company.model.Interfaces;

import com.company.model.User;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;

public interface GetUserByIdRequestInt {

    @SneakyThrows
    public ResponseEntity<User> getUserById();

    public  void printResponce();



}