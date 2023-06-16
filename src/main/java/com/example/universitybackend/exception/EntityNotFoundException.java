package com.example.universitybackend.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class EntityNotFoundException extends RuntimeException {
    @Getter
    private final String description;
    public EntityNotFoundException(String description) {
        super(description);
        this.description = description;
    }
}
