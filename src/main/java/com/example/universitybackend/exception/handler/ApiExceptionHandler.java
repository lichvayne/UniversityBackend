package com.example.universitybackend.exception.handler;

import com.example.universitybackend.exception.EntityNotFoundException;
import com.example.universitybackend.exception.InvalidPropertyException;
import com.example.universitybackend.exception.response.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(InvalidPropertyException.class)
    @ResponseBody
    public ResponseEntity<ApiErrorResponse> invalidPropertyExceptionHandler(InvalidPropertyException exception) {

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now(),
                exception.getMessage()
        );

        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ApiErrorResponse> entityNotFoundExceptionHandler(EntityNotFoundException exception) {

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                HttpStatus.NOT_FOUND,
                LocalDateTime.now(),
                exception.getMessage()
        );

        return new ResponseEntity<>(apiErrorResponse, HttpStatus.NOT_FOUND);

    }



}
