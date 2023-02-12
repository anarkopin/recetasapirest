package com.example.apirestrecetario.repository;

import com.example.apirestrecetario.model.Cocinero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CocineroRepository extends JpaRepository<Cocinero, Long> {

    Cocinero findByEmail(String email);
    Boolean existsByEmail(String email);
}
