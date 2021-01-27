package com.example.ISAISA.controller;

import com.example.ISAISA.model.AdminPharmacy;
import com.example.ISAISA.model.Pharmacy;
import com.example.ISAISA.model.User;
import com.example.ISAISA.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pharmacy")
public class PharmacyController {

    private PharmacyService pharmacyService;

    @Autowired
    public void setPharmacistService(PharmacyService pharmacyService) {
        this.pharmacyService = pharmacyService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMINPHARMACY')")
    public ResponseEntity<Pharmacy> getPharmacy() {
        AdminPharmacy user = (AdminPharmacy) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Pharmacy pharmacy = user.getPharmacy();
        return new ResponseEntity<>(pharmacy, HttpStatus.OK);
    }

}
