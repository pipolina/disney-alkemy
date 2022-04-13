package com.alkemy.disney.dto;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ApiErrorDTO {

    private HttpStatus status;
    private String message;
    private List<String> errors;

    public <T> ApiErrorDTO(HttpStatus badRequest, String message, List<T> param_not_found) {
    }
}
