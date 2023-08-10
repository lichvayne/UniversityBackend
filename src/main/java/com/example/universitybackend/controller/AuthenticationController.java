package com.example.universitybackend.controller;

import com.example.universitybackend.response.AuthResponse;
import com.example.universitybackend.dto.AuthenticationDto;
import com.example.universitybackend.dto.RegisterDto;
import com.example.universitybackend.response.RegistrationResponse;
import com.example.universitybackend.service.impl.AuthenticationServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@Tag(name = "Authentication", description = "The Authentication Api")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationServiceImpl authenticationService;


    @Operation(
            summary = "Register User",
            description = "Register User",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Invalid Email Supplied", responseCode = "400")
            }
    )
    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> register(@RequestBody RegisterDto registerDto){
        return ResponseEntity.ok(authenticationService.register(registerDto));
    }

    @Operation(
            summary = "Authenticate User",
            description = "Authenticate User to generate token",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "User With This Email Not Found", responseCode = "404")
            }
    )
    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authentication(@RequestBody AuthenticationDto authenticationDto){
        return ResponseEntity.ok(authenticationService.authenticate(authenticationDto));
    }
}
