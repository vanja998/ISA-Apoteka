package com.example.ISAISA.controller;

import com.example.ISAISA.model.AdminPharmacy;
import com.example.ISAISA.model.Dermatologist;
import com.example.ISAISA.model.Pharmacy;
import com.example.ISAISA.service.DermatologistService;
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
@RequestMapping(value = "/dermatologists")
public class DermatologistController {

    private DermatologistService dermatologistService;

    @Autowired
    public void setDermatologistService(DermatologistService dermatologistService) {
        this.dermatologistService = dermatologistService;
    }

    @GetMapping(value="/dermatologist",produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('DERMATOLOGIST')")
    public ResponseEntity<Dermatologist> getDermatologist() {
        Dermatologist user = (Dermatologist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value="/dermatologistChange",produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('DERMATOLOGIST')")
    public ResponseEntity<Dermatologist> changeDermatologist(String email, String firstName, String lastName, String address, String phone, String city, String country, Pharmacy pharmacy) {
        Dermatologist user = dermatologistService.changeDermatologist(email, firstName, lastName, address, phone, city, country, pharmacy);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
