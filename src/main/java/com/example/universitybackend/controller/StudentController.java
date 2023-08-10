package com.example.universitybackend.controller;

import com.example.universitybackend.dto.StudentDto;
import com.example.universitybackend.entity.Student;
import com.example.universitybackend.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
@Slf4j
@Tag(name = "Student", description = "The Student Api")
@SecurityRequirement(name = "BearerAuthentication")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Operation(
            summary = "Get All Student",
            description = "Get All Student",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Unauthorized", responseCode = "403"),
                    @ApiResponse(description = "Student Not Found", responseCode = "404")
            }
    )
    @GetMapping
    public ResponseEntity<List<Student>> getStudentAll() {
        log.debug("Received request to get all student");
        return ResponseEntity.ok(studentService.getAllStudent());
    }

    @Operation(
            summary = "Get Student",
            description = "Get Single Student By Id",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Unauthorized", responseCode = "403"),
                    @ApiResponse(description = "Invalid Id Supplied", responseCode = "403"),
                    @ApiResponse(description = "Student Not Found", responseCode = "404")
            }
    )
    @GetMapping("{id}")
    public ResponseEntity<Student> getStudent(@PathVariable @Parameter(description = "Student Id") Long id) {
        log.debug("Received request to get student by id");
        return ResponseEntity.ok(studentService.getStudent(id));
    }

    @Operation(
            summary = "Get Student By PersonalNo",
            description = "Get Single Student By PersonalNo",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Unauthorized", responseCode = "403"),
                    @ApiResponse(description = "Invalid PersonalNo Supplied", responseCode = "403"),
                    @ApiResponse(description = "Student Not Found", responseCode = "404")
            }
    )
    @GetMapping("personalno/{personalNo}")
    public ResponseEntity<Student> getStudentByPersonalNo(@PathVariable @Parameter(description = "Student PersonalNo") String personalNo) {
        log.debug("Received request to get student by PersonalNo");
        return ResponseEntity.ok(studentService.getStudentByPersonalNo(personalNo));
    }

    @Operation(
            summary = "Delete Student",
            description = "Delete Single Student By Id",
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204"),
                    @ApiResponse(description = "Unauthorized", responseCode = "403"),
                    @ApiResponse(description = "Invalid Id Supplied", responseCode = "403"),
                    @ApiResponse(description = "Student Not Found", responseCode = "404")
            }
    )
    @DeleteMapping("{id}")
    public ResponseEntity<Student> deleteStudentById(@PathVariable @Parameter(description = "Student Id") Long id) {
        log.debug("Received request to delete student by id");

        return new ResponseEntity<>(studentService.deleteStudent(id), HttpStatus.NO_CONTENT);
    }

    @Operation(
            summary = "Add Student",
            description = "Add Student",
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201"),
                    @ApiResponse(description = "Unauthorized", responseCode = "403"),
                    @ApiResponse(description = "Invalid Object Supplied", responseCode = "403"),
                    @ApiResponse(description = "Student Not Found", responseCode = "404")
            }
    )
    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody StudentDto studentDto) {
        log.debug("Received request to add student");
        return new ResponseEntity<>(studentService.addStudent(studentDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update Student",
            description = "Update Student By Id",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Unauthorized", responseCode = "403"),
                    @ApiResponse(description = "Invalid Id Supplied", responseCode = "403"),
                    @ApiResponse(description = "Student Not Found", responseCode = "404")
            }
    )
    @PutMapping("{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id,
                                                 @RequestParam(required = false) String firstName,
                                                 @RequestParam(required = false) String lastName,
                                                 @RequestParam(required = false) String personalNo,
                                                 @RequestParam(required = false) String address,
                                                 @RequestParam(required = false) Long courseId) {
        log.debug("Received request to update student");
        return ResponseEntity.ok(studentService.updateStudent(id, firstName, lastName, personalNo, address,courseId));
    }

}
