package com.company.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.company.model.User;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRegistrationAndLoginResponce {

    private String sessionId;
    private User user;

}