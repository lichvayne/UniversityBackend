package com.example.universitybackend.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Arguments Are Invalid")
public class InvalidPropertyException extends RuntimeException{
    @Getter
    private final String description;
    public InvalidPropertyException(String description) {
        super(description);
        this.description = description;
    }
}
