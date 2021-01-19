package com.example.ISAISA.controller;

import com.example.ISAISA.service.AdminPharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
