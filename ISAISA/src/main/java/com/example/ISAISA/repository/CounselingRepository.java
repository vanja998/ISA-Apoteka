package com.example.ISAISA.repository;

import com.example.ISAISA.model.Counseling;
import com.example.ISAISA.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CounselingRepository extends JpaRepository<Counseling, Integer> {
    List<Counseling> findAllByPatient(Patient patient);
}
