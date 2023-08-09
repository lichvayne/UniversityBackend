package com.example.universitybackend.service.impl;

import com.example.universitybackend.exception.EntityNotFoundException;
import com.example.universitybackend.response.AuthResponse;
import com.example.universitybackend.dto.AuthenticationDto;
import com.example.universitybackend.dto.RegisterDto;
import com.example.universitybackend.entity.User;
import com.example.universitybackend.enums.UserRole;
import com.example.universitybackend.exception.EntityAlreadyExistsException;
import com.example.universitybackend.repository.UserRepository;
import com.example.universitybackend.response.RegistrationResponse;
import com.example.universitybackend.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public RegistrationResponse register(RegisterDto registerDto) {
        if(userRepository.existsByEmailIgnoreCase(registerDto.getEmail())){
            throw new EntityAlreadyExistsException("User With This Email Already Exists!");
        }
        User user = new User(registerDto);
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRole(UserRole.USER);
        userRepository.save(user);
        return new RegistrationResponse(true, "User Successfully Registered");
    }

    public AuthResponse authenticate(AuthenticationDto authenticationDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationDto.getEmail(),
                authenticationDto.getPassword()
        ));
        User user = userRepository.findByEmail(authenticationDto.getEmail())
                .orElseThrow( () -> new EntityNotFoundException("User With This Emails Not Found"));
        String jwtToken = jwtService.generateToken(user);
        return new AuthResponse(true,"User Successfully Authenticated", jwtToken);
    }
}
