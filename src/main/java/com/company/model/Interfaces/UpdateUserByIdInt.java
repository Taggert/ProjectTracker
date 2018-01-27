package com.company.model.Interfaces;

import com.company.model.User;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;

public interface UpdateUserByIdInt {

    @SneakyThrows
    public ResponseEntity<User> updateUserById();
    public void printResponce();



}