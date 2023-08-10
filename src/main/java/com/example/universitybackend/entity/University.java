package com.example.universitybackend.entity;

import com.example.universitybackend.dto.UniversityDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "University")
@Data
@NoArgsConstructor
public class University extends AppEntity<Long> {

    @Id
    @SequenceGenerator(
            name = "universityIdSeq",
            sequenceName = "UNIVERSITY_ID_SEQ",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "universityIdSeq",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;

    @Column(
            nullable = false,
            name = "name"
    )
    private String name;

    @Column(name = "address")
    private String address;

    @OneToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinColumn(name = "university_id",referencedColumnName = "id")
    private Set<Course> courses;

    @OneToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinColumn(name = "university_id",referencedColumnName = "id")
    private Set<Student> students;

    public University(UniversityDto dto) {
        this.name = dto.getName();
        this.address = dto.getAddress();
    }

}