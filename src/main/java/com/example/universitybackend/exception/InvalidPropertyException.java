package com.example.universitybackend.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Getter
public class InvalidPropertyException extends RuntimeException{
    private final String message;

    public InvalidPropertyException(String message) {
        super(message);
        this.message = message;
    }

}
