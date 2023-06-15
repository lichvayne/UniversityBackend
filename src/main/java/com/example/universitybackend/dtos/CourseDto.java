package com.example.universitybackend.dtos;

import lombok.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CourseDto {

    private String name;

    private Byte semester;

    private String description;

    private String code;

}
