package com.example.universitybackend.service.impl;

import com.example.universitybackend.dto.UniversityDto;
import com.example.universitybackend.entity.University;
import com.example.universitybackend.exception.EntityNotFoundException;
import com.example.universitybackend.exception.InvalidPropertyException;
import com.example.universitybackend.record.RecordState;
import com.example.universitybackend.repository.UniversityRepository;
import com.example.universitybackend.service.UniversityService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UniversityServiceImpl implements UniversityService {

    private final UniversityRepository universityRepository;

    @Autowired
    public UniversityServiceImpl(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @Override
    public University addUniversity(UniversityDto universityDto) {

        if (universityDto == null) {
            throw new InvalidPropertyException("University is Null");
        }

        if (universityDto.getName() == null) {
            throw new InvalidPropertyException("University name is Null");
        }

        return universityRepository.save(new University(universityDto));

    }


    @Override
    @Transactional
    public University updateUniversity(Long id, String name, String address) {
        if (id == null) {
            throw new InvalidPropertyException("id is Null");
        }

        Optional<University> university = universityRepository.findById(id);

        if (university.isPresent()) {

            University updateUniversity = university.get();

            if(name != null && name.length() >0 && !updateUniversity.getName().equals(name)){

                updateUniversity.setName(name);

            }

            if(address !=null && !updateUniversity.getAddress().equals(address)){

                updateUniversity.setAddress(address);

            }

            return updateUniversity;

        }
        else throw new EntityNotFoundException("University with this Id doesn't exists");

    }




    @Override
    public University deleteUniversity(Long id) {

        if (id == null) {
            throw new InvalidPropertyException("id is Null");
        }

        Optional<University> university = universityRepository.findById(id);

        if(university.isPresent()){

            university.get().setRecordState(RecordState.DELETED);

            return universityRepository.save(university.get());

        }
        else throw new EntityNotFoundException("University with this Id doesn't exists");

    }

    @Override
    public University getUniversity(Long id) {

        if (id == null) {
            throw new InvalidPropertyException("id is null");
        }

        Optional<University> university = universityRepository.findById(id);

        if (university.isPresent()) {

            return university.get();

        }
        else throw new EntityNotFoundException("University with this Id doesn't exists");

    }

    @Override
    public List<University> getUniversityAll() {

        return universityRepository.findAll();

    }
}
