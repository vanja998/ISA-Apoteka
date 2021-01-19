package com.example.ISAISA.controller;

import com.example.ISAISA.model.Patient;
import com.example.ISAISA.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/patients")
public class PatientController {

    private PatientService patientService;

    @Autowired
    public void setPatientService(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/{patientId}")
    @PreAuthorize("hasAuthority('ROLE_PATIENT')")
    public Optional<Patient> loadById(@PathVariable Integer patientId) {
        return this.patientService.findById(patientId);
    }

}
