package com.example.universitybackend.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

public interface JwtService {

    String extractUsername(String jwtToken);
    <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver);

    boolean isTokenValid(String jwtToken, UserDetails userDetails);
    String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);

    String generateToken(UserDetails userDetails);

}
