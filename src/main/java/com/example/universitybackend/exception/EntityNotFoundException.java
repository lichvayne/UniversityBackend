package com.example.universitybackend.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Getter
public class EntityNotFoundException extends RuntimeException {

    private final String message;
    public EntityNotFoundException(String message) {
        super(message);
        this.message = message;
    }

}
