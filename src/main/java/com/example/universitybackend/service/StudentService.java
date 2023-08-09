package com.example.universitybackend.service;

import com.example.universitybackend.dto.StudentDto;
import com.example.universitybackend.entity.Student;

import java.util.List;


public interface StudentService {

    Student getStudent(Long id);
    Student getStudentByPersonalNo(String personalNo);
    List<Student> getAllStudent();

    Student updateStudent(Long id, String name, String lastname, String personalNo, String address, Long courseId);

    Student addStudent(StudentDto studentDto);
    Student deleteStudent(Long id);


}
