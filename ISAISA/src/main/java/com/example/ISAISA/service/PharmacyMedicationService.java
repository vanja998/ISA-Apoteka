package com.example.ISAISA.service;

import com.example.ISAISA.model.Medication;
import com.example.ISAISA.model.Pharmacy;
import com.example.ISAISA.model.PharmacyMedication;
import com.example.ISAISA.repository.PharmacyMedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PharmacyMedicationService {

    private PharmacyMedicationRepository pharmacyMedicationRepository;

    @Autowired
    public void setPharmacyMedicationRepository(PharmacyMedicationRepository pharmacyMedicationRepository) { this.pharmacyMedicationRepository = pharmacyMedicationRepository; }

    public PharmacyMedication findByPharmacyAndMedication(Pharmacy pharmacy, Medication medication) {
        return this.pharmacyMedicationRepository.findOneByPharmacyAndMedication(pharmacy, medication);
    }

}
