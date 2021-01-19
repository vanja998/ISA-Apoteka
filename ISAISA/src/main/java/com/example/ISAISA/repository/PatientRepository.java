package com.example.ISAISA.repository;

import com.example.ISAISA.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

    @Override
    Optional<Patient> findById(Integer integer);
}
