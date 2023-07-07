package com.example.universitybackend.entity;

import com.example.universitybackend.dto.CourseDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Table(name = "Course")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course extends AppEntity<Long> {

    @Id
    @SequenceGenerator(name = "courseIdSec", sequenceName = "COURSE_ID_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "COURSE_ID_SEQ", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "semester")
    private Byte semester;

    @Column(name = "description")
    private String description;

    @Column(name = "credits")
    private Byte credits = 6;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<Student> students;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    public Course(CourseDto courseDto) {
        this.name = courseDto.getName();
        this.semester = courseDto.getSemester();
        this.description = courseDto.getDescription();
        this.code = courseDto.getCode();
    }
}
