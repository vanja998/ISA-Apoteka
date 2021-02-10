package com.example.ISAISA.repository;

import com.example.ISAISA.model.Pharmacist;
import com.example.ISAISA.model.PharmacyMedication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacyMedicationRepository extends JpaRepository<PharmacyMedication, Integer> {
}
