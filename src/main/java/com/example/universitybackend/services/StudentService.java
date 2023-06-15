package com.example.universitybackend.services;

import com.example.universitybackend.dtos.StudentDto;
import com.example.universitybackend.entities.Student;
import com.example.universitybackend.record.RecordState;

import java.util.List;


public interface StudentService {

    Student getStudent(Long id);
    Student getStudentByPersonalNo(String personalNo);
    List<Student> getAllStudent();

    Student updateStudent(Long id, String name, String lastname, String personalNo, String address, String code);


    Student addStudent(StudentDto studentDto);
    Student deleteStudent(Long id);

}
