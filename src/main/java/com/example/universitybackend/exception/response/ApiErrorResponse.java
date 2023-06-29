package com.example.universitybackend.exception.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
public class ApiErrorResponse {

    private HttpStatus status;

    private Integer code;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    private String message;


    public ApiErrorResponse(HttpStatus status, LocalDateTime timestamp, String message) {
        this.status = status;
        this.code = status.value();
        this.timestamp = timestamp;
        this.message = message;
    }

}
