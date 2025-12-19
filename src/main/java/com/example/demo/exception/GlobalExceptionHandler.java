
 

package com.example.demo.exception;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.RestControllerAdvice;

 

import java.util.HashMap;

import java.util.Map;

 

@RestControllerAdvice

public class GlobalExceptionHandler {

   

    @ExceptionHandler(ValidationException.class)

    public ResponseEntity<Map<String, String>> handleValidationException(ValidationException ex) {

        Map<String, String> error = new HashMap<>();

        error.put("error", ex.getMessage());

        return ResponseEntity.badRequest().body(error);

    }

   

    @ExceptionHandler(ResourceNotFoundException.class)

    public ResponseEntity<Map<String, String>> handleResourceNotFoundException(ResourceNotFoundException ex) {

        Map<String, String> error = new HashMap<>();

        error.put("error", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }

}