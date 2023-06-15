package com.example.universitybackend.controller;

import com.example.universitybackend.dtos.CourseDto;
import com.example.universitybackend.entities.Course;
import com.example.universitybackend.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin()
@RestController()
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/api/courses")
    public ResponseEntity<List<Course>> getCourseAll(){

        return new ResponseEntity<>(courseService.getAllCourse(), HttpStatus.ACCEPTED);

    }

    @GetMapping("/api/course/id/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable Long id){

        return new ResponseEntity<>(courseService.getCourse(id), HttpStatus.ACCEPTED);

    }

    @GetMapping("/api/course/name/{name}")
    public ResponseEntity<Course> getCourseByName(@PathVariable String name){

        return new ResponseEntity<>(courseService.getCourseByName(name), HttpStatus.ACCEPTED);

    }

    @PostMapping("/api/course/add")
    public ResponseEntity<Course> addCourse(@RequestBody CourseDto courseDto){

        return new ResponseEntity<>(courseService.addCourse(courseDto), HttpStatus.ACCEPTED);

    }

    @GetMapping("/api/course/code/{code}")
    public ResponseEntity<Course> getCourseByCode(@PathVariable String code){

        return new ResponseEntity<>(courseService.getCourseByCode(code), HttpStatus.ACCEPTED);

    }

    @PutMapping("/api/course/update/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestParam(required = false) String name,
                                               @RequestParam(required = false) Byte semester,@RequestParam(required = false)  String description,
                                               @RequestParam(required = false) String code,  @RequestParam(required = false) Byte credits){

        return new ResponseEntity<>(courseService.updateCourse(id,name,semester,description,code,credits), HttpStatus.ACCEPTED);

    }


    @DeleteMapping("/api/course/delete/{id}")
    public ResponseEntity<Course> deleteCourse(@PathVariable Long id){

        return new ResponseEntity<>(courseService.deleteCourse(id), HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/api/course/delete/code/{code}")
    public ResponseEntity<Course> deleteCourseByCode(@PathVariable String code){

        return new ResponseEntity<>(courseService.deleteCourseByCode(code), HttpStatus.ACCEPTED);

    }



}
