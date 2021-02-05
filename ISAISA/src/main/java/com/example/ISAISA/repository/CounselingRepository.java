package com.example.ISAISA.repository;

import com.example.ISAISA.model.Counseling;
import com.example.ISAISA.model.Pharmacist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CounselingRepository extends JpaRepository<Counseling, Integer> {
    Set<Counseling> findByPharmacist(Pharmacist pharmacist);
}
