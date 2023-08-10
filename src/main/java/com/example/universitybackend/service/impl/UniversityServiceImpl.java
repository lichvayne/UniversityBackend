package com.example.universitybackend.service.impl;

import com.example.universitybackend.dto.UniversityDto;
import com.example.universitybackend.entity.Student;
import com.example.universitybackend.entity.University;
import com.example.universitybackend.exception.EntityNotFoundException;
import com.example.universitybackend.exception.InvalidPropertyException;
import com.example.universitybackend.enums.RecordState;
import com.example.universitybackend.repository.StudentRepository;
import com.example.universitybackend.repository.UniversityRepository;
import com.example.universitybackend.service.UniversityService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class UniversityServiceImpl implements UniversityService {

    private final UniversityRepository universityRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public UniversityServiceImpl(UniversityRepository universityRepository, StudentRepository studentRepository) {
        this.universityRepository = universityRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public University addUniversity(UniversityDto universityDto) {
        if (universityDto == null) {
            throw new InvalidPropertyException("University is Null");
        }
        if (universityDto.getName() == null) {
            throw new InvalidPropertyException("University Name is Null");
        }

        University university = new University(universityDto);
        log.info("Added University: {}", university);
        return universityRepository.save(university);
    }


    @Override
    @Transactional
    public University updateUniversity(Long id, String name, String address, Long studentId) {
        if (id == null) {
            throw new InvalidPropertyException("Id Is Null");
        }

        Optional<University> university = universityRepository.findById(id);
        university.ifPresent(updateUniversity -> {
            if (name != null && name.length() > 0 && !updateUniversity.getName().equals(name)) {
                updateUniversity.setName(name);
            }
            if (address != null && !updateUniversity.getAddress().equals(address)) {
                updateUniversity.setAddress(address);
            }
            if (studentId != null && studentId > 0) {
                studentRepository.findById(studentId).ifPresent(studentToAssign -> {
                    Set<Student> studentSet = updateUniversity.getStudents();
                    studentSet.add(studentToAssign);
                    updateUniversity.setStudents(studentSet);
                });
            }
            universityRepository.save(updateUniversity);
            log.info("Updated university: {}", updateUniversity);
        });

        return university.orElseThrow(() -> new EntityNotFoundException("University With This Id Not Found"));
    }

    @Override
    public University deleteUniversity(Long id) {
        if (id == null) {
            throw new InvalidPropertyException("Id Is Null");
        }

        Optional<University> university = universityRepository.findById(id);
        university.ifPresent(deleteUniversity -> {
            deleteUniversity.setRecordState(RecordState.DELETED);
            universityRepository.save(deleteUniversity);
            log.info("Deleted university: {}", deleteUniversity);
        });

        return university.orElseThrow(() -> new EntityNotFoundException("University With This Id Not Found"));
    }

    @Override
    public University getUniversity(Long id) {
        if (id == null) {
            throw new InvalidPropertyException("Id Is Null");
        }

        Optional<University> university = universityRepository.findById(id);
        university.ifPresent(universityToLog -> log.info("Fetched university: {}", universityToLog));
        return university.orElseThrow(() -> new EntityNotFoundException("University With This Id Not Found"));
    }

    @Override
    public List<University> getUniversityAll() {
        log.info("Fetched All University");
        return universityRepository.findAll();
    }
}
