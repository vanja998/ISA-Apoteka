package com.example.ISAISA.repository;

import com.example.ISAISA.model.Medication;
import com.example.ISAISA.model.Pharmacist;
import com.example.ISAISA.model.Pharmacy;
import com.example.ISAISA.model.PharmacyMedication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Set;

public interface PharmacyMedicationRepository extends JpaRepository<PharmacyMedication, Integer> {
    Set<PharmacyMedication> findAllByMedicationAndBeginPriceValidityBeforeAndEndPriceValidityAfter(Medication medication, LocalDate localDate,LocalDate localDate1);
    Set<PharmacyMedication> findAllByPharmacyAndMedication(Pharmacy pharmacy, Medication medication);

}
