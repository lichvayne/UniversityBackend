package com.example.universitybackend.services;

import com.example.universitybackend.dtos.ApiResponse;
import com.example.universitybackend.dtos.StudentDto;
import com.example.universitybackend.record.RecordState;

public interface StudentService {

    ApiResponse getStudent(Long id);
    ApiResponse getStudentByPersonalNo(String personalNo);
    ApiResponse getAllStudent();
    ApiResponse updateStudentName(Long id, String name);
    ApiResponse updateStudentLastName(Long id, String lastName);
    ApiResponse updateStudentpersonalNo(Long id, String personalNo);
    ApiResponse addStudent(StudentDto studentDto);
    ApiResponse deleteStudent(Long id);

}
