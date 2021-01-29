package com.example.ISAISA.service;

import com.example.ISAISA.model.AdminPharmacy;
import com.example.ISAISA.model.Dermatologist;
import com.example.ISAISA.model.Pharmacy;
import com.example.ISAISA.repository.DermatologistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class DermatologistService {

    private DermatologistRepository dermatologistRepository;

    @Autowired
    public void setDermatologistRepository(DermatologistRepository dermatologistRepository) {
        this.dermatologistRepository = dermatologistRepository;
    }

    public Dermatologist changeDermatologist(String email, String firstName, String lastName, String address, String phone, String city, String country, Pharmacy pharmacy) {
        Dermatologist user = (Dermatologist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAddress(address);
        user.setPhone(phone);
        user.setCity(city);
        user.setCountry(country);
        return user;
    }

}
