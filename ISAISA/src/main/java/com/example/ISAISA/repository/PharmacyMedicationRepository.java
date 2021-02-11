package com.example.ISAISA.repository;

import com.example.ISAISA.model.Medication;
import com.example.ISAISA.model.Pharmacist;
import com.example.ISAISA.model.Pharmacy;
import com.example.ISAISA.model.PharmacyMedication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PharmacyMedicationRepository extends JpaRepository<PharmacyMedication, Integer> {

    Set<PharmacyMedication> findAllByPharmacyAndMedication(Pharmacy pharmacy, Medication medication);

}
