package com.example.ISAISA.controller;

import com.example.ISAISA.DTO.*;
import com.example.ISAISA.model.*;
import com.example.ISAISA.service.EmployeeService;
import com.example.ISAISA.service.MedicationService;
import com.example.ISAISA.service.PharmacyMedicationService;
import com.example.ISAISA.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.util.*;

@RestController
@RequestMapping(value = "/medications")
public class MedicationController {

    private MedicationService medicationService;
    private PharmacyMedicationService pharmacyMedicationService;
    private PharmacyService pharmacyService;

    @Autowired
    public void setMedicationService(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @Autowired
    public void setPharmacyMedicationService(PharmacyMedicationService pharmacyMedicationService) {
        this.pharmacyMedicationService = pharmacyMedicationService;
    }

    @Autowired
    public void setPharmacyService(PharmacyService pharmacyService) {
        this.pharmacyService = pharmacyService;
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

    @GetMapping(value="/adminmedications",produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMINPHARMACY')")
    public ResponseEntity<Set<MedicationPharmacyDTO>> getMedicationsAdmin() {
        AdminPharmacy user = (AdminPharmacy) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<Medication> medications = this.medicationService.getMedicationByPharmacy(user.getPharmacy());

        Set<MedicationPharmacyDTO> medicationPharmacyDTOS = new HashSet<>();
        for(Medication medication : medications) {
            PharmacyMedication pharmacyMedication = pharmacyMedicationService.findByPharmacyAndMedication(user.getPharmacy(), medication);
            MedicationPharmacyDTO mp = new MedicationPharmacyDTO(medication.getId(), medication.getCode(), medication.getName(), medication.getProducer(),
                    pharmacyMedication.getQuantity(), pharmacyMedication.getPrice());

            medicationPharmacyDTOS.add(mp);
        }

        return new ResponseEntity<>(medicationPharmacyDTOS, HttpStatus.OK);
    }

    @PostMapping(value="/adminMedicationSearch", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMINPHARMACY')")
    public ResponseEntity<Set<MedicationPharmacyDTO>> searchMedicationAdmin(@RequestBody MedicationPharmacyDTO medicationPharmacyDTO) {

        AdminPharmacy user = (AdminPharmacy) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<Medication> medications = medicationService.getMedicationByNameAndPharmacy(user.getPharmacy(), medicationPharmacyDTO.getName());

        Set<MedicationPharmacyDTO> medicationPharmacyDTOS = new HashSet<>();
        for(Medication medication : medications) {
            PharmacyMedication pharmacyMedication = pharmacyMedicationService.findByPharmacyAndMedication(user.getPharmacy(), medication);
            MedicationPharmacyDTO mp = new MedicationPharmacyDTO(medication.getId(), medication.getCode(), medication.getName(), medication.getProducer(),
                    pharmacyMedication.getQuantity(), pharmacyMedication.getPrice());

            medicationPharmacyDTOS.add(mp);
        }
        return new ResponseEntity<>(medicationPharmacyDTOS, HttpStatus.OK);
    }

    @GetMapping(value="/adminmedicationsNot",produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMINPHARMACY')")
    public ResponseEntity<Set<Medication>> getNotMedicationsAdmin() {
        AdminPharmacy user = (AdminPharmacy) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<Medication> medicationsNot = medicationService.getMedicationNotInPharmacy(user.getPharmacy());

        return new ResponseEntity<>(medicationsNot, HttpStatus.OK);
    }

    @PostMapping(value="/addMedicineToPharmacy", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMINPHARMACY')")
    public ResponseEntity<PharmacyMedication> addMedicineToPharmacy(@RequestBody MedicationPharmacyDTO medicationPharmacyDTO) {

        AdminPharmacy user = (AdminPharmacy) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Medication medication1 = medicationService.findByName(medicationPharmacyDTO.getName());
        PharmacyMedication pharmacyMedication = new PharmacyMedication(user.getPharmacy(), medication1, 0,
                medicationPharmacyDTO.getPrice(), medicationPharmacyDTO.getBeginPriceValidity(), medicationPharmacyDTO.getEndPriceValidity());
        pharmacyMedication = medicationService.addMedicationToPharmacy(pharmacyMedication);

        return new ResponseEntity<>(pharmacyMedication, HttpStatus.OK);
    }

    @PostMapping(value="/removeMedicineFromPharmacy")
    @PreAuthorize("hasRole('ADMINPHARMACY')")
    public void removeMedicineFromPharmacy(@RequestBody IdDto idDto) throws Exception {

        AdminPharmacy user = (AdminPharmacy) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Medication medication = medicationService.findById(idDto.getId());

        this.medicationService.removeMedicationFromPharmacy(medication, user.getPharmacy());

    }

    @PostMapping(value="/changeMedicationPrice", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMINPHARMACY')")
    public ResponseEntity<PharmacyMedication> changeMedicationPrice(@RequestBody IdPriceDTO idPriceDTO){

        AdminPharmacy user = (AdminPharmacy) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PharmacyMedication pharmacyMedication = medicationService.changeMedication(idPriceDTO.getPrice(), idPriceDTO.getId(), user.getPharmacy(), idPriceDTO.getEnd());

        return new ResponseEntity<>(pharmacyMedication, HttpStatus.OK);
    }

    @PostMapping(value="/changeMedicationQuantity", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMINPHARMACY')")
    public ResponseEntity<PharmacyMedication> changeMedicationQuantity(@RequestBody IdPriceDTO idPriceDTO){

        AdminPharmacy user = (AdminPharmacy) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PharmacyMedication pharmacyMedication = medicationService.changeMedicationQuantity(idPriceDTO.getQuantity(), idPriceDTO.getId(), user.getPharmacy());

        return new ResponseEntity<>(pharmacyMedication, HttpStatus.OK);
    }

    @PostMapping(value="/allMedicationsByPharmacy",produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<Set<MedicationPharmacyDTO>> getMedicationsByPharmacy(@RequestBody IdDto idDto) {

        Pharmacy pharmacy = pharmacyService.findById(idDto.getId());
        Set<Medication> medications = this.medicationService.getMedicationByPharmacy(pharmacy);

        Set<MedicationPharmacyDTO> medicationPharmacyDTOS = new HashSet<>();
        for(Medication medication : medications) {
            PharmacyMedication pharmacyMedication = pharmacyMedicationService.findByPharmacyAndMedication(pharmacy, medication);
            MedicationPharmacyDTO mp = new MedicationPharmacyDTO(medication.getId(), medication.getCode(), medication.getName(), medication.getProducer(),
                    pharmacyMedication.getQuantity(), pharmacyMedication.getPrice());

            medicationPharmacyDTOS.add(mp);
        }

        return new ResponseEntity<>(medicationPharmacyDTOS, HttpStatus.OK);
    }


}