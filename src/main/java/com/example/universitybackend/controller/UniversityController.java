package com.example.universitybackend.controller;

import com.example.universitybackend.dto.UniversityDto;
import com.example.universitybackend.entity.University;
import com.example.universitybackend.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/university")
public class UniversityController {

    private final UniversityService universityService;

    @Autowired
    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @GetMapping
    public ResponseEntity<List<University>> getAllUniversity() {
        return new ResponseEntity<>(universityService.getUniversityAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<University> getUniversity(@PathVariable Long id) {
        return new ResponseEntity<>(universityService.getUniversity(id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<University> deleteUniversityById(@PathVariable Long id) {
        return new ResponseEntity<>(universityService.deleteUniversity(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<University> addUniversity(@RequestBody UniversityDto universityDto) {
        return new ResponseEntity<>(universityService.addUniversity(universityDto), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<University> updateUniversity(@PathVariable Long id,
                                                       @RequestParam(required = false) String name,
                                                       @RequestParam(required = false) String address,
                                                       @RequestParam(required = false) Long studentId) {
        return new ResponseEntity<>(universityService.updateUniversity(id, name, address,studentId), HttpStatus.OK);
    }

}
