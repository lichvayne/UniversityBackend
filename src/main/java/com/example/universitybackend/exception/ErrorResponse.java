package com.example.universitybackend.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponse {

    private HttpStatus status;

    private Integer code;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    private String message;

    public ErrorResponse(HttpStatus status, String message) {
        this.status = status;
        this.code = status.value();
        this.timestamp = LocalDateTime.now();
        this.message = message;
    }

}
