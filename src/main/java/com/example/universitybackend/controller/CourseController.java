package com.example.universitybackend.controller;

import com.example.universitybackend.dto.CourseDto;
import com.example.universitybackend.entity.Course;
import com.example.universitybackend.service.CourseService;
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
@RequestMapping("/api/v1/course")
@Slf4j
@Tag(name = "Course", description = "The Course Api")
@SecurityRequirement(name = "BearerAuthentication")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @Operation(
            summary = "Get All Course",
            description = "Get All Course",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Unauthorized", responseCode = "403"),
                    @ApiResponse(description = "Course Not Found", responseCode = "404")
            }
    )
    @GetMapping
    public ResponseEntity<List<Course>> getCourseAll() {
        log.debug("Received request to get all course");
        return ResponseEntity.ok(courseService.getAllCourse());
    }

    @Operation(
            summary = "Get Course By Id",
            description = "Get Single Course By Id",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Unauthorized", responseCode = "403"),
                    @ApiResponse(description = "Invalid Id Supplied", responseCode = "400"),
                    @ApiResponse(description = "Course Not Found", responseCode = "404")
            }
    )
    @GetMapping("{id}")
    public ResponseEntity<Course> getCourse(@PathVariable @Parameter(description = "Course Id") Long id) {
        log.debug("Received request to get course by Id");
        return ResponseEntity.ok(courseService.getCourse(id));
    }

    @Operation(
            summary = "Get Course By Name",
            description = "Get Single Course By Name",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Unauthorized", responseCode = "403"),
                    @ApiResponse(description = "Invalid Name Supplied", responseCode = "400"),
                    @ApiResponse(description = "Course Not Found", responseCode = "404")
            }
    )
    @GetMapping("name/{name}")
    public ResponseEntity<Course> getCourseByName(@PathVariable @Parameter(description = "Course Name") String name) {
        log.debug("Received request to get course by name");
        return ResponseEntity.ok(courseService.getCourseByName(name));
    }

    @Operation(
            summary = "Add Course",
            description = "Add Course",
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201"),
                    @ApiResponse(description = "Unauthorized", responseCode = "403"),
                    @ApiResponse(description = "Invalid Object Supplied", responseCode = "400"),
                    @ApiResponse(description = "Course Not Found", responseCode = "404")
            }
    )
    @PostMapping
    public ResponseEntity<Course> addCourse(@RequestBody CourseDto courseDto) {
        log.debug("Received request to add course");
        return new ResponseEntity<>(courseService.addCourse(courseDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get Course By Code",
            description = "Get Single Course By Code",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Unauthorized", responseCode = "403"),
                    @ApiResponse(description = "Invalid Code Supplied", responseCode = "400"),
                    @ApiResponse(description = "Course Not Found", responseCode = "404")
            }
    )
    @GetMapping("code/{code}")
    public ResponseEntity<Course> getCourseByCode(@PathVariable @Parameter(description = "Course Code") String code) {
        log.debug("Received request to get course by code");
        return ResponseEntity.ok(courseService.getCourseByCode(code));
    }

    @Operation(
            summary = "Update Course",
            description = "Update Course",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Unauthorized", responseCode = "403"),
                    @ApiResponse(description = "Invalid Id Supplied", responseCode = "400"),
                    @ApiResponse(description = "Course Not Found", responseCode = "404")
            }
    )
    @PutMapping("{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id,
                                               @RequestParam(required = false) String name,
                                               @RequestParam(required = false) Byte semester,
                                               @RequestParam(required = false) String description,
                                               @RequestParam(required = false) String code,
                                               @RequestParam(required = false) Byte credits,
                                               @RequestParam(required = false) Long studentId) {
        log.debug("Received request to update course");
        return ResponseEntity.ok(courseService.updateCourse(id, name, semester, description, code, credits, studentId));
    }

    @Operation(
            summary = "Delete Course",
            description = "Delete Single Course By Id",
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204"),
                    @ApiResponse(description = "Unauthorized", responseCode = "403"),
                    @ApiResponse(description = "Invalid Id Supplied", responseCode = "400"),
                    @ApiResponse(description = "Course Not Found", responseCode = "404")
            }
    )
    @DeleteMapping("{id}")
    public ResponseEntity<Course> deleteCourse(@PathVariable @Parameter(description = "Course Id") Long id) {
        log.debug("Received request to delete course by id");
        return new ResponseEntity<>(courseService.deleteCourse(id), HttpStatus.NO_CONTENT);
    }

    @Operation(
            summary = "Delete Course By Code",
            description = "Delete Single Course By Code",
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204"),
                    @ApiResponse(description = "Unauthorized", responseCode = "403"),
                    @ApiResponse(description = "Invalid Code Supplied", responseCode = "400"),
                    @ApiResponse(description = "Course Not Found", responseCode = "404")
            }
    )
    @DeleteMapping("code/{code}")
    public ResponseEntity<Course> deleteCourseByCode(@PathVariable @Parameter(description = "Course Code") String code) {
        log.debug("Received request to delete course by code");
        return new ResponseEntity<>(courseService.deleteCourseByCode(code), HttpStatus.NO_CONTENT);
    }

}
