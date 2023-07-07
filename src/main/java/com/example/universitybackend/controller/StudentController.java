package com.example.universitybackend.controller;

import com.example.universitybackend.dto.StudentDto;
import com.example.universitybackend.entity.Student;
import com.example.universitybackend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getStudentAll() {
        return new ResponseEntity<>(studentService.getAllStudent(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        return new ResponseEntity<>(studentService.getStudent(id), HttpStatus.OK);
    }

    @GetMapping("personalno/{personalNo}")
    public ResponseEntity<Student> getStudentByPersonalNo(@PathVariable String personalNo) {
        return new ResponseEntity<>(studentService.getStudentByPersonalNo(personalNo), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> deleteStudentById(@PathVariable Long id) {
        return new ResponseEntity<>(studentService.deleteStudent(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody StudentDto studentDto) {
        return new ResponseEntity<>(studentService.addStudent(studentDto), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id,
                                                 @RequestParam(required = false) String firstName,
                                                 @RequestParam(required = false) String lastName,
                                                 @RequestParam(required = false) String personalNo,
                                                 @RequestParam(required = false) String address) {
        return new ResponseEntity<>(studentService.updateStudent(id, firstName, lastName, personalNo, address), HttpStatus.OK);
    }

}
