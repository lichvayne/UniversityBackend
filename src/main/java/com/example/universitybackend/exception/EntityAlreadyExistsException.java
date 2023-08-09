package com.example.universitybackend.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Getter
public class EntityAlreadyExistsException  extends RuntimeException{

    private final String message;
    public EntityAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
}
