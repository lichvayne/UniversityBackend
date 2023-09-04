package com.example.universitybackend.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Hidden
public class UniversityDto {

    private String name;

    private String address;

}
