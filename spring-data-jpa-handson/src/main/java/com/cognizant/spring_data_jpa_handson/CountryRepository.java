package com.cognizant.spring_data_jpa_handson;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {
    
    
    List<Country> findByNameContainingIgnoreCase(String name);
    

    List<Country> findByNameContainingIgnoreCaseOrderByNameAsc(String name);
    

    List<Country> findByNameStartingWith(String name);
}