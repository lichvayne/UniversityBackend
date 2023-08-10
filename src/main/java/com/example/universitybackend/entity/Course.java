package com.example.universitybackend.entity;

import com.example.universitybackend.dto.CourseDto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "Course")
@Data
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Course extends AppEntity<Long> {
    @Id
    @SequenceGenerator(
            name = "courseIdSec",
            sequenceName = "COURSE_ID_SEQ",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "COURSE_ID_SEQ",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;

    @Column(
            name = "name",
            nullable = false,
            unique = true
    )
    private String name;

    @Column(name = "semester")
    private Byte semester;

    @Column(name = "description")
    private String description;

    @Column(name = "credits")
    private Byte credits = 6;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "course_student",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> students;

    @Column(
            name = "code",
            nullable = false,
            unique = true
    )
    private String code;

    public Course(CourseDto courseDto) {
        this.name = courseDto.getName();
        this.semester = courseDto.getSemester();
        this.description = courseDto.getDescription();
        this.code = courseDto.getCode();
    }
}
