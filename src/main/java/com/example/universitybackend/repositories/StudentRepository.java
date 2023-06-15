package com.example.universitybackend.repositories;

import com.example.universitybackend.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    List<Student> findAllByNameAndLastName(String name, String lastname);
    Optional<Student> findById(Long id);
    Optional<Student> findStudentByPersonalNo(String personalNo);

    List<Student> findAllByLastName(String lastname);
}
