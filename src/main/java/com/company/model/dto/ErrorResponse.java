package com.company.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ErrorResponse {

    private int errorStatus;
    private Map<String, List<String>> errorResponse;
    private String message;


}