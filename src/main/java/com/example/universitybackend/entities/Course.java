package com.example.universitybackend.entities;

import com.example.universitybackend.dtos.CourseDto;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
@Table(name = "Course")
public class Course extends AppEntity<Long>{
    @Id
    @SequenceGenerator(name = "courseIdSec",sequenceName = "COURSE_ID_SEQ",allocationSize = 1)
    @GeneratedValue(generator = "COURSE_ID_SEQ",strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name",nullable = false,unique = true)
    private String name;

    @Column(name = "semester")
    private Byte semester;

    @Column(name = "description")
    private String description;

    @Column(name = "credits")
    private Byte credits = 6;

    @Column(name = "code",nullable = false,unique = true)
    private String code;

    public Course(CourseDto courseDto) {
        this.name = courseDto.getName();
        this.semester = courseDto.getSemester();
        this.description = courseDto.getDescription();
        this.code = courseDto.getCode();
    }
}
