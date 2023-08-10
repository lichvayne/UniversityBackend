package com.example.universitybackend.controller;

import com.example.universitybackend.dto.UniversityDto;
import com.example.universitybackend.entity.University;
import com.example.universitybackend.service.UniversityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/university")
@Slf4j
@Tag(name = "University", description = "The University Api")
@SecurityRequirement(name = "BearerAuthentication")
public class UniversityController {

    private final UniversityService universityService;

    @Autowired
    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @Operation(
            summary = "Get All University",
            description = "Get All University",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Unauthorized", responseCode = "403"),
                    @ApiResponse(description = "University Not Found", responseCode = "404")
            }
    )
    @GetMapping
    public ResponseEntity<List<University>> getAllUniversity() {
        log.debug("Received request to get all university");
        return ResponseEntity.ok(universityService.getUniversityAll());
    }

    @Operation(
            summary = "Get University",
            description = "Get Single University By Id",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Unauthorized", responseCode = "403"),
                    @ApiResponse(description = "Invalid Id Supplied", responseCode = "400"),
                    @ApiResponse(description = "University Not Found", responseCode = "404")
            }
    )
    @GetMapping("{id}")
    public ResponseEntity<University> getUniversity(@PathVariable @Parameter(description = "University Id") Long id) {
        log.debug("Received request to get  university by id");
        return ResponseEntity.ok(universityService.getUniversity(id));
    }

    @Operation(
            summary = "Delete University",
            description = "Delete University By Id",
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204"),
                    @ApiResponse(description = "Unauthorized", responseCode = "403"),
                    @ApiResponse(description = "Invalid Id Supplied", responseCode = "400"),
                    @ApiResponse(description = "University Not Found", responseCode = "404")
            }
    )
    @DeleteMapping("{id}")
    public ResponseEntity<University> deleteUniversityById(@PathVariable @Parameter(description = "University Id") Long id) {
        log.debug("Received request to get delete university by id");
        return new ResponseEntity<>(universityService.deleteUniversity(id), HttpStatus.NO_CONTENT);
    }

    @Operation(
            summary = "Add University",
            description = "Add University",
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201"),
                    @ApiResponse(description = "Unauthorized", responseCode = "403"),
                    @ApiResponse(description = "Invalid Object Supplied", responseCode = "400"),
                    @ApiResponse(description = "University Not Found", responseCode = "404")
            }
    )
    @PostMapping
    public ResponseEntity<University> addUniversity(@RequestBody UniversityDto universityDto) {
        log.debug("Received request to add university");
        return new ResponseEntity<>(universityService.addUniversity(universityDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update University",
            description = "Update University By Id",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Unauthorized", responseCode = "403"),
                    @ApiResponse(description = "Invalid Id Supplied", responseCode = "400"),
                    @ApiResponse(description = "University Not Found", responseCode = "404")
            }
    )
    @PutMapping("{id}")
    public ResponseEntity<University> updateUniversity(@PathVariable Long id,
                                                       @RequestParam(required = false) String name,
                                                       @RequestParam(required = false) String address,
                                                       @RequestParam(required = false) Long studentId) {
        log.debug("Received request to update university");
        return ResponseEntity.ok(universityService.updateUniversity(id, name, address,studentId));
    }

}
