package com.example.universitybackend.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Hidden
public class StudentDto {

    private String firstName;

    private String lastName;

    private String personalNo;

    private String address;

}
