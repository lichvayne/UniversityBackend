package com.example.universitybackend.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Hidden
public class CourseDto {

    private String name;

    private Byte semester;

    private String description;

    private String code;

}
