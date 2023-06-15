package com.example.universitybackend.services;

import com.example.universitybackend.dtos.UniversityDto;
import com.example.universitybackend.entities.University;

import java.util.List;

public interface UniversityService {
    University addUniversity(UniversityDto universityDto);
    University updateUniversity(Long id, String name,String address);
    University deleteUniversity(Long id);
    University getUniversity(Long id);
    List<University> getUniversityAll();
}
