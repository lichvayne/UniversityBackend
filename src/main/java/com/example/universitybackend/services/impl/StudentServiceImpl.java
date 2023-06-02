package com.example.universitybackend.services.impl;

import com.example.universitybackend.dtos.ApiResponse;
import com.example.universitybackend.dtos.StudentDto;
import com.example.universitybackend.entities.Student;
import com.example.universitybackend.record.RecordState;
import com.example.universitybackend.repositories.StudentRepository;
import com.example.universitybackend.services.StudentService;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

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
    public ApiResponse getStudent(Long id) {

        if (id == null) {
            return new ApiResponse().addError("Incorrect Parameter", "id is null");
        }

        Optional<Student> student = studentRepository.findById(id);

        if (student.isPresent()) {

            return new ApiResponse("Student", student.get());

        }
        else return new ApiResponse().addError("Student not found", "Incorrect id");

    }

    @Override
    public ApiResponse getStudentByPersonalNo(String personalNo) {
        if (personalNo == null) {
            return new ApiResponse().addError("Incorrect Parameter", "personalNo is null");
        }

        Optional<Student> student = studentRepository.findStudentByPersonalNo(personalNo);

        if (student.isPresent()) {

            return new ApiResponse("Student", student.get());

        }
        else return new ApiResponse().addError("Student not found", "Incorrect personalNo");
    }

    @Override
    public ApiResponse getAllStudent() {

        List<Student> studentsList = studentRepository.findAll();

        return new ApiResponse().addData("Students List", studentsList);

    }

    @Override
    public ApiResponse updateStudentName(Long id, String name) {

        if (name == null) {
            return new ApiResponse().addError("Incorrect Parameter", "Name is Null");
        }

        if (id == null) {
            return new ApiResponse().addError("Incorrect Parameter", "Id is Null");
        }

        Optional<Student> student = studentRepository.findById(id);

        if (student.isPresent()) {

            student.get().setName(name);

            Student save = studentRepository.save(student.get());

            return new ApiResponse("Student Name update", save);

        }
        else return new ApiResponse().addError("Student not found", "Incorrect id");

    }

    @Override
    public ApiResponse updateStudentLastName(Long id, String lastName) {
        if (lastName == null) {
            return new ApiResponse().addError("Incorrect Parameter", "Name is Null");
        }

        if (id == null) {
            return new ApiResponse().addError("Incorrect Parameter", "Id is Null");
        }

        Optional<Student> student = studentRepository.findById(id);

        if (student.isPresent()) {
            student.get().setLastName(lastName);
            Student save = studentRepository.save(student.get());

            return new ApiResponse("Student lastname update", save);
        }
        else return new ApiResponse().addError("Student not found", "Incorrect id");

    }

    @Override
    public ApiResponse updateStudentpersonalNo(Long id, String personalNo) {

        if (personalNo == null) {
            return new ApiResponse().addError("Incorrect Parameter", "personalNo is Null");
        }

        if (id == null) {
            return new ApiResponse().addError("Incorrect Parameter", "Id is Null");
        }

        Optional<Student> student = studentRepository.findById(id);

        if (student.isPresent()) {

            student.get().setPersonalNo(personalNo);

            Student save = studentRepository.save(student.get());

            return new ApiResponse("Student lastname update", save);
        }
        else return new ApiResponse().addError("Student not found", "Incorrect id");

    }

    @Override
    public ApiResponse addStudent(StudentDto studentDto) {

        if (studentDto == null) {
            return new ApiResponse().addError("Incorrect Parameter", "Student is Null");
        }

        if (studentDto.getPersonalNo() == null) {
            return new ApiResponse().addError("Incorrect Parameter", "Student personalNo is Null");
        }

        if (studentDto.getName() == null || studentDto.getLastName() == null) {

            Student student = new Student(studentDto);

            student.setRecordState(RecordState.DRAFT);

            Student save = studentRepository.save(student);

            return new ApiResponse().addData("Student Draft", save);
        }

        Student save = studentRepository.save(new Student(studentDto));

        return new ApiResponse().addData("Student", save);

    }

    @Override
    public ApiResponse deleteStudent(Long id) {
        if (id == null) {
            return new ApiResponse("Incorrect Parameter", "id is Null");
        }

        Optional<Student> student = studentRepository.findById(id);

        if(student.isPresent()){

            student.get().setRecordState(RecordState.DELETED);

            Student save = studentRepository.save(student.get());

            return new ApiResponse().addData("Student deleted",save);
        }
        else return new ApiResponse().addError("Student not found","Incorrect id");

    }
}
