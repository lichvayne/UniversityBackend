package com.example.universitybackend.controller;

import com.example.universitybackend.dto.CourseDto;
import com.example.universitybackend.entity.Course;
import com.example.universitybackend.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<Course>> getCourseAll() {
        return new ResponseEntity<>(courseService.getAllCourse(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Course> getCourse(@PathVariable Long id) {
        return new ResponseEntity<>(courseService.getCourse(id), HttpStatus.OK);
    }

    @GetMapping("name/{name}")
    public ResponseEntity<Course> getCourseByName(@PathVariable String name) {
        return new ResponseEntity<>(courseService.getCourseByName(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Course> addCourse(@RequestBody CourseDto courseDto) {
        return new ResponseEntity<>(courseService.addCourse(courseDto), HttpStatus.OK);
    }

    @GetMapping("code/{code}")
    public ResponseEntity<Course> getCourseByCode(@PathVariable String code) {
        return new ResponseEntity<>(courseService.getCourseByCode(code), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id,
                                               @RequestParam(required = false) String name,
                                               @RequestParam(required = false) Byte semester,
                                               @RequestParam(required = false) String description,
                                               @RequestParam(required = false) String code,
                                               @RequestParam(required = false) Byte credits,
                                               @RequestParam(required = false) Long studentId ) {
        return new ResponseEntity<>(courseService.updateCourse(id, name, semester, description, code, credits,studentId), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Course> deleteCourse(@PathVariable Long id) {
        return new ResponseEntity<>(courseService.deleteCourse(id), HttpStatus.OK);
    }

    @DeleteMapping("code/{code}")
    public ResponseEntity<Course> deleteCourseByCode(@PathVariable String code) {
        return new ResponseEntity<>(courseService.deleteCourseByCode(code), HttpStatus.OK);
    }

}
