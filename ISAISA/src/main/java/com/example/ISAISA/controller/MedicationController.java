package com.example.ISAISA.controller;

import com.example.ISAISA.DTO.PharmacistDTO;
import com.example.ISAISA.DTO.PharmacyDTO;
import com.example.ISAISA.model.AdminPharmacy;
import com.example.ISAISA.model.Medication;
import com.example.ISAISA.model.Pharmacy;
import com.example.ISAISA.service.EmployeeService;
import com.example.ISAISA.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/medications")
public class MedicationController {

    private MedicationService medicationService;

    @Autowired
    public void setMedicationService(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @GetMapping(value="/allmedications",produces = MediaType.APPLICATION_JSON_VALUE)                                           // value nije naveden, jer koristimo bazni url
    public ResponseEntity<List<Medication>> getMedications() {
        List<Medication> medicationList = this.medicationService.findAll();

        // Kreiramo listu DTO objekata
        List<Medication> medicationsDTOS = new ArrayList<>();

        for (Medication medication : medicationList) {
            Medication medications = new Medication(medication.getId(),medication.getCode(),medication.getName(),medication.getType_med(),medication.getShape_med(),medication.getIngredients(),medication.getProducer(),medication.getPrescription(),medication.getNotes());
            medicationsDTOS.add(medications);
        }
        return new ResponseEntity<>(medicationsDTOS, HttpStatus.OK);
    }

    @PostMapping(value="/MedicationsSearch", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Medication>> getMedicationsbyName(@RequestBody Medication medicationDTO) {

        Set<Medication> medications = medicationService.getMedicationsbyName(medicationDTO.getName());
        return new ResponseEntity<>(medications, HttpStatus.OK);
    }


}