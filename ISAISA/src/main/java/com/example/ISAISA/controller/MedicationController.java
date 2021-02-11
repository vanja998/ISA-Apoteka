package com.example.ISAISA.controller;

import com.example.ISAISA.DTO.*;
import com.example.ISAISA.model.AdminPharmacy;
import com.example.ISAISA.model.Medication;
import com.example.ISAISA.model.Pharmacy;
import com.example.ISAISA.model.PharmacyMedication;
import com.example.ISAISA.repository.MedicationRepository;
import com.example.ISAISA.repository.PharmacyMedicationRepository;
import com.example.ISAISA.service.EmployeeService;
import com.example.ISAISA.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
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

    @Autowired
    private PharmacyMedicationRepository pharmacyMedicationRepository;

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

    @PostMapping(value="/MedicationsSearchh", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Medication>> getMedicationsbyNamee(@RequestBody Medication medicationDTO) {

        Set<Medication> medications = medicationService.getMedicationsbyNamee(medicationDTO.getName());

        return new ResponseEntity<>(medications, HttpStatus.OK);
    }

    @PostMapping(value="/getpharmaciesprice", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<PharmacyMedicationPriceDTO>> getPharmacyAndMedicationPrice(@RequestBody IdDto idDto) {

        Set<PharmacyMedicationPriceDTO> pharmacyMedicationPriceDTOS= new HashSet<PharmacyMedicationPriceDTO>();
        Medication medication= medicationService.findById(idDto.getId());
        LocalDate localDate=LocalDate.now();
        Set<PharmacyMedication> pharmacyMedications= this.pharmacyMedicationRepository.findAllByMedicationAndBeginPriceValidityBeforeAndEndPriceValidityAfter(medication,localDate,localDate);

        for (PharmacyMedication pharmacyMedication: pharmacyMedications){
            PharmacyMedicationPriceDTO pharmacyMedicationPriceDTO= new PharmacyMedicationPriceDTO();
            pharmacyMedicationPriceDTO.setName(pharmacyMedication.getPharmacy().getName());
            pharmacyMedicationPriceDTO.setPrice(pharmacyMedication.getPrice());
            pharmacyMedicationPriceDTOS.add(pharmacyMedicationPriceDTO);
        }

        return new ResponseEntity<>(pharmacyMedicationPriceDTOS, HttpStatus.OK);
    }


    @PostMapping(value="/getOneMedication",produces = MediaType.APPLICATION_JSON_VALUE)                                           // value nije naveden, jer koristimo bazni url
    public ResponseEntity<Medication> getMedications(@RequestBody IdDto idDto) {
        Medication medication=medicationService.findById(idDto.getId());

        return new ResponseEntity<Medication>(medication, HttpStatus.OK);
    }

    @GetMapping(value="/getTypeMedication",produces = MediaType.APPLICATION_JSON_VALUE)                                           // value nije naveden, jer koristimo bazni url
    public ResponseEntity<Set<MedicationTypeDTO>> getTypes() {
        List<Medication> medicationList = this.medicationService.findAll();
        Set<MedicationTypeDTO> medicationTypeDTOS= new HashSet<MedicationTypeDTO>();
        Set<String> strings=new HashSet<String>();

        for (Medication medication: medicationList){
            String type= medication.getType_med();
            strings.add(type);
        }

        for (String type1: strings){
            MedicationTypeDTO medicationTypeDTO= new MedicationTypeDTO();
            medicationTypeDTO.setType(type1);
            medicationTypeDTOS.add(medicationTypeDTO);
        }


        return new ResponseEntity<Set<MedicationTypeDTO>>(medicationTypeDTOS, HttpStatus.OK);
    }


    @PostMapping(value="/filterType",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Medication>> filterTypes(@RequestBody MedicationTypeDTO medicationTypeDT0){

        List<Medication> medications = this.medicationService.findAll();
        List<Medication> medications1= new ArrayList<Medication>();

        for(Medication medication: medications){
            String a=medication.getType_med();
            String b=medicationTypeDT0.getType();
            if(a.equals(b)){
                medications1.add(medication);
            }
        }


        return new ResponseEntity<>(medications1, HttpStatus.OK);
    }


}