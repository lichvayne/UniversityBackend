package com.example.universitybackend.repository;

import com.example.universitybackend.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UniversityRepository extends JpaRepository<University,Long> {

    List<University> findAllByNameAndAddress(String name, String address);
    List<University> findAllByName(String name);

}
