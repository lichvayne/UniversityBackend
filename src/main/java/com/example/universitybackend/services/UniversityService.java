package com.example.universitybackend.services;

import com.example.universitybackend.dtos.ApiResponse;
import com.example.universitybackend.dtos.UniversityDto;

public interface UniversityService {
    ApiResponse addUniversity(UniversityDto universityDto);
    ApiResponse updateUniversityName(String name, Long id);
    ApiResponse updateUniversityAddress(String address, Long id);
    ApiResponse deleteUniversity(Long id);
    ApiResponse getUniversity(Long id);
    ApiResponse getUniversityAll();
}
