package com.example.ISAISA.controller;

import com.example.ISAISA.service.DermatologistService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
