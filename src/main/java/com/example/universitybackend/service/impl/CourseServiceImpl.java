package com.example.universitybackend.service.impl;

import com.example.universitybackend.dto.CourseDto;
import com.example.universitybackend.entity.Course;
import com.example.universitybackend.exception.EntityNotFoundException;
import com.example.universitybackend.exception.InvalidPropertyException;
import com.example.universitybackend.record.RecordState;
import com.example.universitybackend.repository.CourseRepository;
import com.example.universitybackend.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {

        this.courseRepository = courseRepository;

    }

    @Override
    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourse(Long id) {

        if(id == null){

            throw new InvalidPropertyException("Id is null");

        }

        if(id <= 0){

            throw new InvalidPropertyException("Id is Incorrect");

        }

        Optional<Course> course = courseRepository.findById(id);

        if(course.isPresent()){

            return course.get();

        }
        else throw new EntityNotFoundException("Course With this ID not Found");

    }

    @Override
    public Course getCourseByName(String name) {
        if(name == null){

            throw new InvalidPropertyException("Name is null");

        }

        Optional<Course> course = courseRepository.findByNameIgnoreCase(name.trim());

        if(course.isPresent()){

            return  course.get();

        }
        else throw new EntityNotFoundException("Course With this Name not Found");

    }

    @Override
    @Transactional
    public Course updateCourse(Long id, String name, Byte semester, String description, String code, Byte credits) {
        if(id == null){

            throw new InvalidPropertyException("Id is Null");

        }

        Optional<Course> course = courseRepository.findById(id);

        if(course.isPresent()){

            Course updateCourse = course.get();

            if(name != null && name.length() > 0 && !updateCourse.getName().equals(name)){

                updateCourse.setName(name);

            }

            if(semester != null && !updateCourse.getSemester().equals(semester)){

                updateCourse.setName(name);

            }

            if(description != null && description.length() > 0 && !updateCourse.getDescription().equals(description)){

                updateCourse.setDescription(description);

            }

            if(credits != null){

                updateCourse.setCredits(credits);

            }

            if(code != null && code.length() > 0 && !updateCourse.getCode().equals(code)){

                Optional<Course> checkCourse = courseRepository.findByCodeIgnoreCase(code);

                if(checkCourse.isEmpty()){

                    updateCourse.setCode(code);

                }

            }

            return updateCourse;

        }
        throw new EntityNotFoundException("Course With this Id Doesn't Exists");

    }

    @Override
    public Course getCourseByCode(String code) {

        if(code == null){
            throw new InvalidPropertyException("code is null");
        }

        Optional<Course> course = courseRepository.findByCodeIgnoreCase(code.trim());


        if(course.isPresent()){

            return  course.get();

        }
        else throw new EntityNotFoundException("Course With this Code not Found");

    }

    @Override
    public Course addCourse(CourseDto courseDto) {

        if(courseDto == null){

            throw new InvalidPropertyException("Course Object is Null");

        }

        Course course = new Course(courseDto);

        return courseRepository.save(course);

    }

    @Override
    public Course deleteCourse(Long id) {
        if(id == null){

            throw new InvalidPropertyException("Id is null");

        }

        if(id <= 0){

            throw new InvalidPropertyException("Id is Incorrect");

        }

        Optional<Course> course = courseRepository.findById(id);

        if(course.isPresent()){

            Course deletedCourse = course.get();

            deletedCourse.setRecordState(RecordState.DELETED);

            return courseRepository.save(deletedCourse);

        }
        else throw new EntityNotFoundException("Course With this ID not Found");

    }

    @Override
    public Course deleteCourseByCode(String code) {
        if(code == null){

            throw new InvalidPropertyException("Id is null");

        }

        Optional<Course> course = courseRepository.findByCodeIgnoreCase(code.trim());

        if(course.isPresent()){

            Course deletedCourse = course.get();

            deletedCourse.setRecordState(RecordState.DELETED);

            return courseRepository.save(deletedCourse);

        }
        else throw new EntityNotFoundException("Course With this Code not Found");

    }

}
