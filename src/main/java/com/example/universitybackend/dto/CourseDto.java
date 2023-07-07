package com.example.universitybackend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {

    private String name;

    private Byte semester;

    private String description;

    private String code;

}
