package com.example.ISAISA.service;

import com.example.ISAISA.repository.DermatologistRepository;
import com.example.ISAISA.repository.Dermatologist_PharmacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Dermatologist_PharmacyService {

    private Dermatologist_PharmacyRepository dermatologist_pharmacyRepository;

    @Autowired
    public void setDermatologist_pharmacyRepository(Dermatologist_PharmacyRepository dermatologist_pharmacyRepository) {
        this.dermatologist_pharmacyRepository = dermatologist_pharmacyRepository;
    }
}
