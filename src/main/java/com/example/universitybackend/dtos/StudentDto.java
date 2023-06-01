package com.example.universitybackend.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentDto {
    private String name;
    private String lastName;
    private String personalNo;
}
