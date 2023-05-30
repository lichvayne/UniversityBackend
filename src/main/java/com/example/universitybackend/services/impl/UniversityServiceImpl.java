package com.example.universitybackend.services.impl;

import com.example.universitybackend.dtos.ApiResponse;
import com.example.universitybackend.dtos.UniversityDto;
import com.example.universitybackend.entities.University;
import com.example.universitybackend.repositories.UniversityRepository;
import com.example.universitybackend.services.UniversityService;
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
    public ApiResponse addUniversity(UniversityDto universityDto) {

        if (universityDto == null) {
            return new ApiResponse().addError("Incorrect Parameter", "University is Null");
        }

        if (universityDto.getName() == null) {
            return new ApiResponse().addError("Incorrect Parameter", "University name is Null");
        }

        University savedUniversity = universityRepository.save(new University(universityDto));

        return new ApiResponse("university", savedUniversity);
    }

    @Override
    public ApiResponse updateUniversityName(String name, Long id) {
        if (name == null) {
            return new ApiResponse().addError("Incorrect Parameter", "Name is Null");
        }

        if (id == null) {
            return new ApiResponse().addError("Incorrect Parameter", "Id is Null");
        }

        Optional<University> university = universityRepository.findById(id);

        if (university.isPresent()) {
            university.get().setName(name);
            University save = universityRepository.save(university.get());
            return new ApiResponse("University", save);
        } else {
            return new ApiResponse().addError("University not found", "Incorrect id");
        }
    }

    @Override
    public ApiResponse updateUniversityAddress(String address, Long id) {
        if (address == null) {
            return new ApiResponse("Incorrect Parameter", "Address is Null");
        }

        if (id == null) {
            return new ApiResponse("Incorrect Parameter", "id is Null");
        }

        Optional<University> university = universityRepository.findById(id);

        if (university.isPresent()) {
            university.get().setAddress(address);
            University save = universityRepository.save(university.get());
            return new ApiResponse("University", save);
        }
        else return new ApiResponse().addError("University not found", "Incorrect id");

    }

    @Override
    public ApiResponse deleteUniversity(Long id) {
        if (id == null) {
            return new ApiResponse("Incorrect Parameter", "id is Null");
        }

        Optional<University> university = universityRepository.findById(id);

        if(university.isPresent()){
            universityRepository.deleteById(id);
            return new ApiResponse().addData("University deleted",university.get());
        }
        else return new ApiResponse().addError("University not found","Incorrect id");

    }

    @Override
    public ApiResponse getUniversity(Long id) {
        if (id == null) {
            return new ApiResponse().addError("Incorrect Parameter", "id is null");
        }
        else {

            Optional<University> university = universityRepository.findById(id);

            if (university.isPresent()) {
                return new ApiResponse("University", university.get());
            }
            else return new ApiResponse().addError("University not found", "Incorrect id");

        }
    }

    @Override
    public ApiResponse getUniversityAll() {

        List<University> universityList = universityRepository.findAll();

        return new ApiResponse("All University", universityList);
    }
}
