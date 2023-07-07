package com.example.universitybackend.service;

import com.example.universitybackend.dto.CourseDto;
import com.example.universitybackend.entity.Course;
import java.util.List;


public interface CourseService {

    List<Course> getAllCourse();

    Course getCourse(Long id);

    Course getCourseByName(String name);

    Course updateCourse(Long id, String name, Byte semester, String description, String code, Byte credits, Long studentId);

    Course getCourseByCode(String code);

    Course addCourse(CourseDto courseDto);

    Course deleteCourse(Long id);

    Course deleteCourseByCode(String code);


}
