package com.example.ISAISA.controller;

import com.example.ISAISA.model.AdminPharmacy;
import com.example.ISAISA.model.Pharmacy;
import com.example.ISAISA.model.User;
import com.example.ISAISA.service.AdminPharmacyService;
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
@RequestMapping(value = "/pharmacyadmins")
public class AdminPharmacyController {

    private AdminPharmacyService adminPharmacyService;

    @Autowired
    public void setAdminPharmacyService(AdminPharmacyService adminPharmacyService) {
        this.adminPharmacyService = adminPharmacyService;
    }

    @GetMapping(value="/admin",produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMINPHARMACY')")
    public ResponseEntity<AdminPharmacy> getAdmin() {
        AdminPharmacy user = (AdminPharmacy) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value="/adminChange",produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMINPHARMACY')")
    public ResponseEntity<AdminPharmacy> changeAdmin(String email, String firstName, String lastName, String address, String phone, String city, String country, Pharmacy pharmacy) {
        AdminPharmacy user = adminPharmacyService.changeAdminPharmacy(email, firstName, lastName, address, phone, city, country, pharmacy);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
