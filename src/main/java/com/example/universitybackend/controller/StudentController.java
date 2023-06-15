package com.example.universitybackend.controller;

import com.example.universitybackend.dtos.StudentDto;
import com.example.universitybackend.entities.Student;
import com.example.universitybackend.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin()
@RestController()
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {

        this.studentService = studentService;

    }

    @GetMapping("/api/students")
    public ResponseEntity<List<Student>> getStudentAll(){

        return new ResponseEntity<>(studentService.getAllStudent(), HttpStatus.ACCEPTED);

    }

    @GetMapping("/api/student/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id){

        return new ResponseEntity<>(studentService.getStudent(id), HttpStatus.ACCEPTED);

    }
    @GetMapping("/api/student/personalno/{personalNo}")
    public ResponseEntity<Student> getStudentByPersonalNo(@PathVariable String personalNo){

        return new ResponseEntity<>(studentService.getStudentByPersonalNo(personalNo), HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/api/delete-student/{id}")
    public ResponseEntity<Student> deleteStudentById(@PathVariable Long id){

        return new ResponseEntity<>(studentService.deleteStudent(id), HttpStatus.ACCEPTED);

    }

    @PostMapping("/api/add-student")
    public ResponseEntity<Student> addStudent(@RequestBody StudentDto studentDto){

        return new ResponseEntity<>(studentService.addStudent(studentDto), HttpStatus.ACCEPTED);

    }

    @PutMapping("/api/update-student/{id}")
    public ResponseEntity<Student> updateStudentLastName(@PathVariable Long id, @RequestParam(required = false) String name, @RequestParam(required = false) String lastName,
                                                         @RequestParam(required = false) String personalNo, @RequestParam(required = false) String address,
                                                         @RequestParam(required = false) String code) {

        return new ResponseEntity<>(studentService.updateStudent(id,name,lastName,personalNo,address,code), HttpStatus.ACCEPTED);

    }


}
