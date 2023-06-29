package com.example.universitybackend.entity;

import com.example.universitybackend.dto.StudentDto;
import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Table(name = "Student")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student extends AppEntity<Long>{
    @Id
    @SequenceGenerator(name = "studentIdSeq",sequenceName = "STUDENT_ID_SEQ",allocationSize = 1)
    @GeneratedValue(generator = "STUDENT_ID_SEQ",strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "personal_no",nullable = false,unique = true)
    private String personalNo;

    @Column(name = "first_name",nullable = true)
    private String firstName;

    @Column(name = "last_name",nullable = true)
    private String lastName;

    @Column(name = "address",nullable = true)
    private String address;

    @ManyToMany
    Set<Course> courses;

    @ManyToOne
    private University university;

    public Student(StudentDto studentDto){
        this.firstName = studentDto.getFirstName();
        this.lastName = studentDto.getLastName();
        this.personalNo = studentDto.getPersonalNo();
        this.address = studentDto.getAddress();
    }

}
