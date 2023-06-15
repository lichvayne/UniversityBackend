package com.example.universitybackend.services.impl;

import com.example.universitybackend.dtos.StudentDto;
import com.example.universitybackend.entities.Course;
import com.example.universitybackend.entities.Student;
import com.example.universitybackend.exception.EntityNotFoundException;
import com.example.universitybackend.exception.InvalidPropertyException;
import com.example.universitybackend.record.RecordState;
import com.example.universitybackend.repositories.CourseRepository;
import com.example.universitybackend.repositories.StudentRepository;
import com.example.universitybackend.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Student getStudent(Long id) {

        if (id == null) {
            throw new InvalidPropertyException("Id Is Null");
        }

        if (id <= 0) {
            throw new InvalidPropertyException("Id is Incorrect");
        }

        Optional<Student> student = studentRepository.findById(id);

        if (student.isPresent()) {

            return student.get();

        }
        else throw new EntityNotFoundException("Student With this ID not Found");

    }

    @Override
    public Student getStudentByPersonalNo(String personalNo) {

        if (personalNo == null) {
            throw new InvalidPropertyException("PersonalNo is Null");
        }
        if (personalNo.trim().length()!=11 || personalNo.trim().contains(" ")) {
            throw new InvalidPropertyException("PersonalNo is Incorrect");
        }

        Optional<Student> student = studentRepository.findStudentByPersonalNo(personalNo.trim());

        if (student.isPresent()) {

            return student.get();

        }
        else throw new EntityNotFoundException("Student With this ID not Found");
    }

    @Override
    public List<Student> getAllStudent() {

        return studentRepository.findAll();

    }

    @Override
    @Transactional
    public Student updateStudent(Long id, String name, String lastname, String personalNo, String address, String code) {

        if (id == null) {
            throw new InvalidPropertyException("Id is null");
        }

        if (id <= 0) {
            throw new InvalidPropertyException("Id is Incorrect");
        }

        Optional<Student> student = studentRepository.findById(id);

        if (student.isPresent()) {

            Student updateStudent = student.get();

            if(name != null && name.length() > 0 && !updateStudent.getName().equals(name)){

                updateStudent.setName(name);

            }

            if(lastname != null && lastname.length() > 0 && !updateStudent.getLastName().equals(lastname)){

                updateStudent.setLastName(lastname);

            }

            if(address !=null && !updateStudent.getAddress().equals(address)){

                updateStudent.setAddress(address);

            }

            if(code != null){

                Optional<Course> course = courseRepository.findByCodeIgnoreCase(code);

                if(course.isPresent()){

                    Set<Course> courseSet = updateStudent.getCourses();

                    courseSet.add(course.get());

                    updateStudent.setCourses(courseSet);
                    
                }
                else  throw  new EntityNotFoundException("Course With this code Doesn't Exists");

            }

            if(personalNo != null && personalNo.length() == 11 && !updateStudent.getPersonalNo().equals(personalNo)){

                Optional<Student> checkStudent = studentRepository.findStudentByPersonalNo(personalNo);

                if(checkStudent.isEmpty()){

                    updateStudent.setLastName(lastname);

                }

            }

            return updateStudent;

        }
        else throw new EntityNotFoundException("Student With this ID not Found");

    }

    @Override
    public Student addStudent(StudentDto studentDto) {

        if (studentDto == null) {
            throw new InvalidPropertyException("Student Object is null");
        }

        if (studentDto.getPersonalNo() == null) {
            throw new InvalidPropertyException("Student Object's PersonalNo is null");
        }

        if (studentDto.getName() == null || studentDto.getLastName() == null) {

            Student student = new Student(studentDto);

            student.setRecordState(RecordState.DRAFT);

            return  studentRepository.save(student);

        }

        return studentRepository.save(new Student(studentDto));

    }

    @Override
    public Student deleteStudent(Long id) {
        if (id == null) {
            throw new InvalidPropertyException("Id is null");
        }

        if (id <= 0) {
            throw new InvalidPropertyException("Id is Incorrect");
        }

        Optional<Student> student = studentRepository.findById(id);

        if (student.isPresent()) {

            student.get().setRecordState(RecordState.DELETED);

            return studentRepository.save(student.get());

        }
        else throw new EntityNotFoundException("Student With this ID not Found");

    }
}
