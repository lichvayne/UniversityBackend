package com.example.universitybackend.controller;

import com.example.universitybackend.dtos.UniversityDto;
import com.example.universitybackend.entities.University;
import com.example.universitybackend.services.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class UniversityController {

    private final UniversityService universityService;
    @Autowired
    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @GetMapping("/api/universities")
    public ResponseEntity<List<University>> getAllUniversity(){

        return new ResponseEntity<>(universityService.getUniversityAll(), HttpStatus.ACCEPTED);

    }

    @GetMapping("/api/university/{id}")
    public ResponseEntity<University> getUniversity(@PathVariable Long id){

        return new ResponseEntity<>(universityService.getUniversity(id), HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/api/delete-university/{id}")
    public ResponseEntity<University> deleteStudentById(@PathVariable Long id){

        return new ResponseEntity<>(universityService.deleteUniversity(id), HttpStatus.ACCEPTED);

    }

    @PostMapping("/api/add-university")
    public ResponseEntity<University> addStudent(@RequestBody UniversityDto universityDto){

        return new ResponseEntity<>(universityService.addUniversity(universityDto), HttpStatus.ACCEPTED);

    }

    @PutMapping("/api/update-university/{id}")
    public ResponseEntity<University> updateStudentLastName(@PathVariable Long id, @RequestParam(required = false) String name, @RequestParam(required = false) String address){

        return new ResponseEntity<>(universityService.updateUniversity(id,name,address), HttpStatus.ACCEPTED);

    }

}
