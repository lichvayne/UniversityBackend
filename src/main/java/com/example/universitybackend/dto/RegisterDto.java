package com.example.universitybackend.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Hidden
public class RegisterDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
