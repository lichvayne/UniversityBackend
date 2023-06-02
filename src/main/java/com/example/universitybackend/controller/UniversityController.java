package com.example.universitybackend.controller;

import com.example.universitybackend.dtos.ApiResponse;
import com.example.universitybackend.dtos.StudentDto;
import com.example.universitybackend.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UniversityController {

    private final StudentService studentService;

    @Autowired
    public UniversityController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/api/students")
    public ResponseEntity<ApiResponse> getStudentAll(){
        return new ResponseEntity<>(studentService.getAllStudent(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/api/student/{id}")
    public ResponseEntity<ApiResponse> getStudent(@PathVariable Long id){
        return new ResponseEntity<>(studentService.getStudent(id), HttpStatus.ACCEPTED);
    }

    @GetMapping("/api/delete-student/{id}")
    public ResponseEntity<ApiResponse> deleteStudentById(@PathVariable Long id){
        return new ResponseEntity<>(studentService.deleteStudent(id), HttpStatus.ACCEPTED);
    }

    @GetMapping("/api/add-student")
    public ResponseEntity<ApiResponse> addStudent(@RequestBody StudentDto studentDto){
        return new ResponseEntity<>(studentService.addStudent(studentDto), HttpStatus.ACCEPTED);
    }

    @PutMapping("/api/update-student-name/{id}")
    public ResponseEntity<ApiResponse> updateStudentName(@PathVariable Long id, @RequestBody String name){
        return new ResponseEntity<>(studentService.updateStudentName(id,name), HttpStatus.ACCEPTED);
    }

    @PutMapping("/api/update-student-lastname/{id}")
    public ResponseEntity<ApiResponse> updateStudentLastName(@PathVariable Long id, @RequestBody String lastName){
        return new ResponseEntity<>(studentService.updateStudentName(id,lastName), HttpStatus.ACCEPTED);
    }

    @PutMapping("/api/update-student-personalno/{id}")
    public ResponseEntity<ApiResponse> updateStudentPersonalNo(@PathVariable Long id, @RequestBody String personalNo){
        return new ResponseEntity<>(studentService.updateStudentName(id,personalNo), HttpStatus.ACCEPTED);
    }

}
