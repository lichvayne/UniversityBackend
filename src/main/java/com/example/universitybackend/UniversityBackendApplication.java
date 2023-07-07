package com.example.universitybackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UniversityBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniversityBackendApplication.class, args);
    }

}
