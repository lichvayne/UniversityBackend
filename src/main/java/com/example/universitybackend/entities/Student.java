package com.example.universitybackend.entities;

import com.example.universitybackend.dtos.StudentDto;
import com.example.universitybackend.record.RecordState;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@Table(name = "Student")
@Entity
public class Student extends AppEntity<Long>{
    @Id
    @SequenceGenerator(name = "studentIdSeq",sequenceName = "STUDENT_ID_SEQ",allocationSize = 1)
    @GeneratedValue(generator = "STUDENT_ID_SEQ",strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "personal_no",nullable = false,unique = true)
    private String personalNo;

    @Column(name = "name",nullable = true)
    private String name;

    @Column(name = "last_name",nullable = true)
    private String lastName;

    @ManyToOne
    private University university;



    public Student(StudentDto studentDto){
        this.name = studentDto.getName();
        this.lastName = studentDto.getLastName();
        this.personalNo = studentDto.getPersonalNo();
    }

}
