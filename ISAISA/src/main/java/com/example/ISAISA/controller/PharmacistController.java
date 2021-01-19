package com.example.ISAISA.controller;

import com.example.ISAISA.service.PharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pharmacists")
public class PharmacistController {

    private PharmacistService pharmacistService;

    @Autowired
    public void setPharmacistService(PharmacistService pharmacistService) {
        this.pharmacistService = pharmacistService;
    }
}
