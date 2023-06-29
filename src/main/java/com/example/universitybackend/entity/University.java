package com.example.universitybackend.entity;

import com.example.universitybackend.dto.UniversityDto;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "University")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class University extends AppEntity<Long> {

    @Id
    @SequenceGenerator(name = "universityIdSeq", sequenceName = "UNIVERSITY_ID_SEQ",allocationSize = 1)
    @GeneratedValue(generator = "universityIdSeq",strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false,name = "name")
    private String name;

    @Column(nullable = true,name = "address")
    private String address;

    @OneToMany
    private List<Student> student;

    public University(UniversityDto dto) {
        this.name = dto.getName();
        this.address = dto.getAddress();
    }

}