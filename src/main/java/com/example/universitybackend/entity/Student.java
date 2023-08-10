package com.example.universitybackend.entity;

import com.example.universitybackend.dto.StudentDto;;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Table(name = "Student")
@Data
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Student extends AppEntity<Long>{
    @Id
    @SequenceGenerator(
            name = "Student_Id_Seq",
            sequenceName = "STUDENT_ID_SEQ",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "STUDENT_ID_SEQ",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;

    @Column(
            name = "personal_no",
            nullable = false,
            unique = true
    )
    private String personalNo;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "address")
    private String address;

    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "students")
    private Set<Course> courses;

    @ManyToOne
    private University university;

    public Student(StudentDto studentDto){
        this.firstName = studentDto.getFirstName();
        this.lastName = studentDto.getLastName();
        this.personalNo = studentDto.getPersonalNo();
        this.address = studentDto.getAddress();
    }

}
