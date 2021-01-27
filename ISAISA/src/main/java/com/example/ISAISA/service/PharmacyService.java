package com.example.ISAISA.service;

import com.example.ISAISA.repository.PharmacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PharmacyService {

    private PharmacyRepository pharmacyRepository;

    @Autowired
    public void setPharmacistRepository(PharmacyRepository pharmacyRepository) {
        this.pharmacyRepository = pharmacyRepository;
    }
}
