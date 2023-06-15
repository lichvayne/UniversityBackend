package com.example.universitybackend.services;

import com.example.universitybackend.dtos.CourseDto;
import com.example.universitybackend.dtos.StudentDto;
import com.example.universitybackend.entities.Course;

import java.util.List;


public interface CourseService {

    List<Course> getAllCourse();

    Course getCourse(Long id);

    Course getCourseByName(String name);

    Course updateCourse(Long id, String name, Byte semester, String description, String code, Byte credits);

    Course getCourseByCode(String code);

    Course addCourse(CourseDto courseDto);

    Course deleteCourse(Long id);

    Course deleteCourseByCode(String code);


}
