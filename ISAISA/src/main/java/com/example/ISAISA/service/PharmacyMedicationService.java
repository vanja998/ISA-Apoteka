package com.example.ISAISA.service;

import com.example.ISAISA.repository.PharmacyMedicationRepository;
import org.springframework.stereotype.Service;

@Service
public class PharmacyMedicationService {

    private PharmacyMedicationRepository pharmacyMedicationRepository;

    public void setPharmacyMedicationRepository(PharmacyMedicationRepository pharmacyMedicationRepository) { this.pharmacyMedicationRepository = pharmacyMedicationRepository; }


}
