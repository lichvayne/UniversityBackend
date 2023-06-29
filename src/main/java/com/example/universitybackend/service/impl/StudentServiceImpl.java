package com.example.universitybackend.service.impl;

import com.example.universitybackend.dto.StudentDto;
import com.example.universitybackend.entity.Course;
import com.example.universitybackend.entity.Student;
import com.example.universitybackend.exception.EntityNotFoundException;
import com.example.universitybackend.exception.InvalidPropertyException;
import com.example.universitybackend.record.RecordState;
import com.example.universitybackend.repository.CourseRepository;
import com.example.universitybackend.repository.StudentRepository;
import com.example.universitybackend.service.StudentService;
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
            throw new InvalidPropertyException("ID Is Null");
        }

        if (id <= 0) {
            throw new InvalidPropertyException("Invalid ID Supplied");
        }

        Optional<Student> student = studentRepository.findById(id);
        return student.orElseThrow(() -> new EntityNotFoundException("Student with this ID not found"));
    }

    @Override
    public Student getStudentByPersonalNo(String personalNo) {
        if (personalNo == null) {
            throw new InvalidPropertyException("PersonalNo is Null");
        }

        if (personalNo.trim().length() != 11 || personalNo.trim().contains(" ")) {
            throw new InvalidPropertyException("PersonalNo is Incorrect");
        }

        Optional<Student> student = studentRepository.findStudentByPersonalNo(personalNo.trim());
        return student.orElseThrow(() -> new EntityNotFoundException("Student with this ID not found"));
    }

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    @Transactional
    public Student updateStudent(Long id, String firstName, String lastName, String personalNo, String address, String code) {

        if (id == null) {
            throw new InvalidPropertyException("Id is null");
        }

        if (id <= 0) {
            throw new InvalidPropertyException("Id is Incorrect");
        }

        Optional<Student> student = studentRepository.findById(id);

        if (student.isPresent()) {

            Student updateStudent = student.get();

            if (updateStudent.getRecordState() == RecordState.DRAFT) {

                updateStudent.setRecordState(RecordState.ACTIVE);

            }

            if (firstName != null && firstName.length() > 0) {

                updateStudent.setFirstName(firstName);

            }

            if (lastName != null && lastName.length() > 0) {

                updateStudent.setLastName(lastName);

            }

            if (address != null) {

                updateStudent.setAddress(address);

            }

            if (code != null) {

                Optional<Course> course = courseRepository.findByCodeIgnoreCase(code);

                if (course.isPresent()) {

                    Set<Course> courseSet = updateStudent.getCourses();

                    courseSet.add(course.get());

                    updateStudent.setCourses(courseSet);

                } else throw new EntityNotFoundException("Course With this code Doesn't Exists");

            }

            if (personalNo != null && personalNo.length() == 11) {

                Optional<Student> checkStudent = studentRepository.findStudentByPersonalNo(personalNo);

                if (checkStudent.isEmpty()) {

                    updateStudent.setLastName(lastName);

                }

            }


            return updateStudent;

        } else throw new EntityNotFoundException("Student with this ID not found");

    }

    @Override
    public Student addStudent(StudentDto studentDto) {

        if (studentDto == null) {
            throw new InvalidPropertyException("Student Object is null");
        }

        if (studentDto.getPersonalNo() == null) {
            throw new InvalidPropertyException("Student Object's PersonalNo is null");
        }

        if (studentDto.getFirstName() == null || studentDto.getLastName() == null) {
            Student student = new Student(studentDto);
            student.setRecordState(RecordState.DRAFT);
            return studentRepository.save(student);
        }

        return studentRepository.save(new Student(studentDto));

    }

    @Override
    public Student deleteStudent(Long id) {
        if (id == null) {
            throw new InvalidPropertyException("ID Is Null");
        }

        if (id <= 0) {
            throw new InvalidPropertyException("Invalid ID Supplied");
        }

        Optional<Student> student = studentRepository.findById(id);
        student.ifPresent((value) -> student.get().setRecordState(RecordState.DELETED));
        return student.orElseThrow(() -> new EntityNotFoundException("Student with this ID not found"));

    }
}
