package com.example.universitybackend.service.impl;

import com.example.universitybackend.dto.StudentDto;
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

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student getStudent(Long id) {
        if (id == null) {
            throw new InvalidPropertyException("ID Is Null");
        }
        if (id <= 0) {
            throw new InvalidPropertyException("Invalid ID Supplied");
        }

        return studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student With This ID Not Found"));
    }

    @Override
    public Student getStudentByPersonalNo(String personalNo) {
        if (personalNo == null) {
            throw new InvalidPropertyException("Personal No is Null");
        }
        if (personalNo.length() != 11 || personalNo.contains(" ")) {
            throw new InvalidPropertyException("Invalid Personal No Supplied");
        }

        return studentRepository.findStudentByPersonalNo(personalNo)
                .orElseThrow(() -> new EntityNotFoundException("Student With This ID Not Found"));
    }

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    @Transactional
    public Student updateStudent(Long id, String firstName, String lastName, String personalNo, String address) {
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
        student.ifPresent((deleteStudent) -> deleteStudent.setRecordState(RecordState.DELETED));

        return student.orElseThrow(() -> new EntityNotFoundException("Student With This ID Not Found"));
    }
}
