package com.example.universitybackend.entities;

import com.example.universitybackend.dtos.StudentDto;
import jakarta.persistence.*;
import lombok.*;
/**
დავალება
 რეკორდი უნდა შევქმნათ enum recordstate  active, inactive, deleted, draft

 StudentRepository
 StudentService
 StudentServiceImpl

 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name = "Student")
@Entity
public class Student {
    @Id
    @SequenceGenerator(name = "studentIdSec",sequenceName = "STUDENT_ID_SEC",allocationSize = 1)
    @GeneratedValue(generator = "STUDENT_ID_SEC",strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "personal_no",nullable = false,unique = true)
    private String personalNo;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "last_name",nullable = false)
    private String lastName;
    @ManyToOne
    private University university;

    public Student(StudentDto studentDto){
        this.name = studentDto.getName();
        this.lastName = studentDto.getLastName();
        this.personalNo = studentDto.getPersonalNo();
    }

}
