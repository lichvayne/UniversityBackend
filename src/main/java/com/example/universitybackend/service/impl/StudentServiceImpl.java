package com.example.universitybackend.service.impl;

import com.example.universitybackend.dto.StudentDto;
import com.example.universitybackend.entity.Course;
import com.example.universitybackend.entity.Student;
import com.example.universitybackend.exception.EntityNotFoundException;
import com.example.universitybackend.exception.InvalidPropertyException;
import com.example.universitybackend.enums.RecordState;
import com.example.universitybackend.repository.CourseRepository;
import com.example.universitybackend.repository.StudentRepository;
import com.example.universitybackend.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Override
    public Student getStudent(Long id) {
        if (id == null) {
            throw new InvalidPropertyException("ID Is Null");
        }
        if (id <= 0) {
            throw new InvalidPropertyException("Invalid ID Supplied");
        }
        Optional<Student> student = studentRepository.findById(id);
        student.ifPresent(studentToLog -> log.info("Fetched student : {}", studentToLog));
        return student.orElseThrow(() -> new EntityNotFoundException("Student With This ID Not Found"));
    }

    @Override
    public Student getStudentByPersonalNo(String personalNo) {
        if (personalNo == null) {
            throw new InvalidPropertyException("Personal No is Null");
        }
        if (personalNo.length() != 11 || personalNo.contains(" ")) {
            throw new InvalidPropertyException("Invalid Personal No Supplied");
        }
        Optional<Student> student = studentRepository.findStudentByPersonalNo(personalNo);
        student.ifPresent(studentToLog -> log.info("Fetched student : {}", studentToLog));
        return student.orElseThrow(() -> new EntityNotFoundException("Student With This ID Not Found"));
    }

    @Override
    public List<Student> getAllStudent() {
        log.info("All Students Fetched");
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(Long id, String firstName, String lastName, String personalNo, String address, Long courseId) {
        if (id == null) {
            throw new InvalidPropertyException("ID Is Null");
        }
        if (id <= 0) {
            throw new InvalidPropertyException("Invalid ID Supplied");
        }

        Optional<Student> student = studentRepository.findById(id);
        student.ifPresent(updateStudent -> {
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
            if (personalNo != null && personalNo.length() == 11 && studentRepository.findStudentByPersonalNo(personalNo).isEmpty()) {
                updateStudent.setPersonalNo(personalNo);
            }
            if (courseId != null && courseId >= 0) {
                courseRepository.findById(courseId).ifPresent(course -> {
                    Set<Course> courseList = updateStudent.getCourses();
                    courseList.add(course);
                    updateStudent.setCourses(courseList);
                });
            }
            studentRepository.save(updateStudent);
            log.info("Updated student : {}", updateStudent);
        });

        return student.orElseThrow(() -> new EntityNotFoundException("Student With This ID Not Found"));
    }

    @Override
    public Student addStudent(StudentDto studentDto) {
        if (studentDto == null) {
            throw new InvalidPropertyException("Student Object Is Null");
        }
        if (studentDto.getPersonalNo() == null) {
            throw new InvalidPropertyException("Student PersonalNo Is Null");
        }

        Student student = new Student(studentDto);
        if (studentDto.getFirstName() == null || studentDto.getLastName() == null) {
            student.setRecordState(RecordState.DRAFT);
        }
        log.info("Added student : {}", student);

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
        student.ifPresent((deleteStudent) -> {
            deleteStudent.setRecordState(RecordState.DELETED);
            studentRepository.save(deleteStudent);
            log.info("Student Deleted : {}", deleteStudent);
        });

        return student.orElseThrow(() -> new EntityNotFoundException("Student With This ID Not Found"));
    }
}
