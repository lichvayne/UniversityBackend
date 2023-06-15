package com.example.universitybackend.repositories;


import com.example.universitybackend.entities.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UniversityRepository extends JpaRepository<University,Long> {

    List<University> findAllByNameAndAddress(String name, String address);
    List<University> findAllByName(String name);
    Optional<University> findById(Long id);

}
