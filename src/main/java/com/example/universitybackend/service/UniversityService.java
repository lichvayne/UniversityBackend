package com.example.universitybackend.service;

import com.example.universitybackend.dto.UniversityDto;
import com.example.universitybackend.entity.University;
import java.util.List;

public interface UniversityService {
    University addUniversity(UniversityDto universityDto);
    University updateUniversity(Long id, String name,String address, Long studentId);
    University deleteUniversity(Long id);
    University getUniversity(Long id);
    List<University> getUniversityAll();
}
