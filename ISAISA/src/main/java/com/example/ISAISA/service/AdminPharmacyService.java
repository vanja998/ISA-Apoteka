package com.example.ISAISA.service;

import com.example.ISAISA.model.AdminPharmacy;
import com.example.ISAISA.model.Pharmacy;
import com.example.ISAISA.repository.AdminPharmacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AdminPharmacyService {

    private AdminPharmacyRepository adminPharmacyRepository;

    @Autowired
    public void setAdminPharmacyRepository(AdminPharmacyRepository adminPharmacyRepository) {
        this.adminPharmacyRepository = adminPharmacyRepository;
    }

    public AdminPharmacy changeAdminPharmacy(String email, String firstName, String lastName, String address, String phone, String city, String country, Pharmacy pharmacy) {
        AdminPharmacy user = (AdminPharmacy) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAddress(address);
        user.setPhone(phone);
        user.setCity(city);
        user.setCountry(country);
        user.setPharmacy(pharmacy);
        return user;
    }
}
