package com.example.universitybackend.service.impl;

import com.example.universitybackend.dto.CourseDto;
import com.example.universitybackend.entity.Course;
import com.example.universitybackend.exception.EntityNotFoundException;
import com.example.universitybackend.exception.InvalidPropertyException;
import com.example.universitybackend.record.RecordState;
import com.example.universitybackend.repository.CourseRepository;
import com.example.universitybackend.repository.StudentRepository;
import com.example.universitybackend.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourse(Long id) {
        if (id == null) {
            throw new InvalidPropertyException("Id is null");
        }
        if (id <= 0) {
            throw new InvalidPropertyException("Id is Incorrect");
        }

        Optional<Course> course = courseRepository.findById(id);
        return course.orElseThrow(() -> new EntityNotFoundException("Course With This ID Not Found"));
    }

    @Override
    public Course getCourseByName(String name) {
        if (name == null) {
            throw new InvalidPropertyException("Name is null");
        }

        Optional<Course> course = courseRepository.findByNameIgnoreCase(name.trim());
        return course.orElseThrow(() -> new EntityNotFoundException("Course With This ID Not Found"));
    }

    @Override
    @Transactional
    public Course updateCourse(Long id, String name, Byte semester, String description, String code, Byte credits, Long studentId) {
        if (id == null) {
            throw new InvalidPropertyException("Id Is Null");
        }

        Optional<Course> course = courseRepository.findById(id);
        course.ifPresent(updateCourse -> {
            if (name != null && name.length() > 0 && !updateCourse.getName().equals(name)) {
                updateCourse.setName(name);
            }
            if (semester != null && !updateCourse.getSemester().equals(semester)) {
                updateCourse.setName(name);
            }
            if (description != null && description.length() > 0 && !updateCourse.getDescription().equals(description)) {
                updateCourse.setDescription(description);
            }
            if (credits != null) {
                updateCourse.setCredits(credits);
            }
            if (studentId != null && studentRepository.existsById(studentId)) {
                courseRepository.findById(id)
                        .get()
                        .getStudents()
                        .add(studentRepository.findById(studentId).get());
            }
            if (code != null && code.length() > 0 && !updateCourse.getCode().equals(code) && courseRepository.findByCodeIgnoreCase(code).isEmpty()) {
                updateCourse.setCode(code);
            }
        });

        return course.orElseThrow(() -> new EntityNotFoundException("Course With This ID Not Found"));
    }

    @Override
    public Course getCourseByCode(String code) {
        if (code == null) {
            throw new InvalidPropertyException("Code Is Null");
        }

        Optional<Course> course = courseRepository.findByCodeIgnoreCase(code.trim());
        return course.orElseThrow(() -> new EntityNotFoundException("Course With This ID Not Found"));
    }

    @Override
    public Course addCourse(CourseDto courseDto) {
        if (courseDto == null) {
            throw new InvalidPropertyException("Course Object Is Null");
        }

        Course course = new Course(courseDto);
        return courseRepository.save(course);
    }

    @Override
    public Course deleteCourse(Long id) {
        if (id == null) {
            throw new InvalidPropertyException("Id is null");
        }
        if (id <= 0) {
            throw new InvalidPropertyException("Invalid Id Supplied");
        }

        Optional<Course> course = courseRepository.findById(id);
        course.ifPresent(deleteCourse -> deleteCourse.setRecordState(RecordState.DELETED));

        return course.orElseThrow(() -> new EntityNotFoundException("Course With This ID Not Found"));
    }

    @Override
    public Course deleteCourseByCode(String code) {
        if (code == null) {
            throw new InvalidPropertyException("Id is null");
        }

        Optional<Course> course = courseRepository.findByCodeIgnoreCase(code.trim());
        course.ifPresent(deleteCourse -> deleteCourse.setRecordState(RecordState.DELETED));

        return course.orElseThrow(() -> new EntityNotFoundException("Course With This ID Not Found"));
    }

}
