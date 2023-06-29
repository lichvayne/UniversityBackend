package com.example.universitybackend.controller.api;

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
        return new ResponseEntity<>(studentService.getAllStudent(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        return new ResponseEntity<>(studentService.getStudent(id), HttpStatus.ACCEPTED);
    }

    @GetMapping("/personalno/{personalNo}")
    public ResponseEntity<Student> getStudentByPersonalNo(@PathVariable String personalNo) {
        return new ResponseEntity<>(studentService.getStudentByPersonalNo(personalNo), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudentById(@PathVariable Long id) {
        return new ResponseEntity<>(studentService.deleteStudent(id), HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody StudentDto studentDto) {
        return new ResponseEntity<>(studentService.addStudent(studentDto), HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudentLastName(@PathVariable Long id, @RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName,
                                                         @RequestParam(required = false) String personalNo, @RequestParam(required = false) String address,
                                                         @RequestParam(required = false) String code) {

        return new ResponseEntity<>(studentService.updateStudent(id, firstName, lastName, personalNo, address, code), HttpStatus.ACCEPTED);

    }


}
