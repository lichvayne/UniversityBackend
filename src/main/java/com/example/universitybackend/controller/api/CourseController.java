package com.example.universitybackend.controller.api;

import com.example.universitybackend.dto.CourseDto;
import com.example.universitybackend.entity.Course;
import com.example.universitybackend.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController("/api")
public class CourseController {
    private final CourseService courseService;
    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getCourseAll(){
        return new ResponseEntity<>(courseService.getAllCourse(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable Long id){

        return new ResponseEntity<>(courseService.getCourse(id), HttpStatus.ACCEPTED);

    }

//    @GetMapping("/courses/name/{name}")
//    public ResponseEntity<Course> getCourseByName(@PathVariable String name){
//
//        return new ResponseEntity<>(courseService.getCourseByName(name), HttpStatus.ACCEPTED);
//
//    }

    @PostMapping("/courses")
    public ResponseEntity<Course> addCourse(@RequestBody CourseDto courseDto){

        return new ResponseEntity<>(courseService.addCourse(courseDto), HttpStatus.ACCEPTED);

    }

    @GetMapping("/courses/code/{code}")
    public ResponseEntity<Course> getCourseByCode(@PathVariable String code){

        return new ResponseEntity<>(courseService.getCourseByCode(code), HttpStatus.ACCEPTED);

    }

    @PutMapping("/courses")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestParam(required = false) String name,
                                               @RequestParam(required = false) Byte semester,@RequestParam(required = false)  String description,
                                               @RequestParam(required = false) String code,  @RequestParam(required = false) Byte credits){

        return new ResponseEntity<>(courseService.updateCourse(id,name,semester,description,code,credits), HttpStatus.ACCEPTED);

    }


    @DeleteMapping("/courses")
    public ResponseEntity<Course> deleteCourse(@RequestParam Long id){

        return new ResponseEntity<>(courseService.deleteCourse(id), HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/courses/code/{code}")
    public ResponseEntity<Course> deleteCourseByCode(@PathVariable String code){

        return new ResponseEntity<>(courseService.deleteCourseByCode(code), HttpStatus.ACCEPTED);

    }



}
