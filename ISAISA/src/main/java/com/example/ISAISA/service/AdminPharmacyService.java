package com.example.ISAISA.service;

import com.example.ISAISA.repository.AdminPharmacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminPharmacyService {

    private AdminPharmacyRepository adminPharmacyRepository;

    @Autowired
    public void setAdminPharmacyRepository(AdminPharmacyRepository adminPharmacyRepository) {
        this.adminPharmacyRepository = adminPharmacyRepository;
    }
}
