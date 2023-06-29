package com.example.universitybackend.repository;

import com.example.universitybackend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    List<Student> findAllByFirstNameAndLastName(String firstName, String lastName);
    Optional<Student> findStudentByPersonalNo(String personalNo);

    List<Student> findAllByLastName(String lastname);
}
