package com.example.ISAISA.controller;

import com.example.ISAISA.DTO.IdDto;
import com.example.ISAISA.DTO.PharmacyDTO;
import com.example.ISAISA.model.AdminPharmacy;
import com.example.ISAISA.model.Pharmacy;
import com.example.ISAISA.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/pharmacy")
public class PharmacyController {

    private PharmacyService pharmacyService;

    @Autowired
    public void setPharmacistService(PharmacyService pharmacyService) {
        this.pharmacyService = pharmacyService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<Pharmacy> getPharmacyById(@RequestBody IdDto idDto) {
        //AdminPharmacy user = (AdminPharmacy) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Pharmacy pharmacy = pharmacyService.findById(idDto.getId());
        return new ResponseEntity<>(pharmacy, HttpStatus.OK);
    }

    @GetMapping(value="/allpharmacies",produces = MediaType.APPLICATION_JSON_VALUE)                                           // value nije naveden, jer koristimo bazni url
    public ResponseEntity<List<PharmacyDTO>> getPharmacies() {
        List<Pharmacy> pharmacyList = this.pharmacyService.findAll();

        // Kreiramo listu DTO objekata
        List<PharmacyDTO> pharmaciesDTOS = new ArrayList<>();

        for (Pharmacy pharmacy : pharmacyList) {
            PharmacyDTO pharmacyDTO = new PharmacyDTO(pharmacy.getId(),pharmacy.getName(),pharmacy.getAddress(),pharmacy.getDescription(), pharmacy.getRating());
            pharmaciesDTOS.add(pharmacyDTO);
        }
        return new ResponseEntity<>(pharmaciesDTOS, HttpStatus.OK);
    }

    @PostMapping(value="/PharmaciesSearch", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Pharmacy>> getPharmaciesbyname(@RequestBody Pharmacy pharmacyDTO) {

        Set<Pharmacy> pharmacies = pharmacyService.getPharmaciesbyname(pharmacyDTO.getName());

        return new ResponseEntity<>(pharmacies, HttpStatus.OK);
    }

    @GetMapping(value="/allpharmaciessortbyname",produces = MediaType.APPLICATION_JSON_VALUE)                                           // value nije naveden, jer koristimo bazni url
    public ResponseEntity<List<Pharmacy>> sortPharmacyByName() {
        List<Pharmacy> pharmacyList = this.pharmacyService.findAll();


        // Kreiramo listu DTO objekata
        List<Pharmacy> pharmaciesDTOS = new ArrayList<>();
        ArrayList<String> lista_naziva = new ArrayList<>();

        for (Pharmacy pharmacy : pharmacyList) {
            lista_naziva.add(pharmacy.getName());

        }
        java.util.Collections.sort(lista_naziva);
        for(String naz: lista_naziva){
            Pharmacy pharmacy=pharmacyService.findByName(naz);
            pharmaciesDTOS.add(pharmacy);
        }


        return new ResponseEntity<>(pharmaciesDTOS, HttpStatus.OK);
    }

    @GetMapping(value="/allpharmaciessortbyaddress",produces = MediaType.APPLICATION_JSON_VALUE)                                           // value nije naveden, jer koristimo bazni url
    public ResponseEntity<List<Pharmacy>> sortPharmacyByAddress() throws AccessDeniedException {
        List<Pharmacy> pharmacyList = this.pharmacyService.findAll();


        // Kreiramo listu DTO objekata
        List<Pharmacy> pharmaciesDTOS = new ArrayList<>();
        ArrayList<String> lista_naziva = new ArrayList<>();

        for (Pharmacy pharmacy : pharmacyList) {
            lista_naziva.add(pharmacy.getAddress());

        }
        java.util.Collections.sort(lista_naziva);
        for(String naz: lista_naziva){
            Pharmacy pharmacy=pharmacyService.findByAddress(naz);
            pharmaciesDTOS.add(pharmacy);
        }


        return new ResponseEntity<>(pharmaciesDTOS, HttpStatus.OK);
    }


    @GetMapping(value="/allpharmaciessortbyrating",produces = MediaType.APPLICATION_JSON_VALUE)                                           // value nije naveden, jer koristimo bazni url
    public ResponseEntity<List<Pharmacy>> sortPharmacyByRating(){
        List<Pharmacy> pharmacyList = this.pharmacyService.findAll();


        // Kreiramo listu DTO objekata
        List<Pharmacy> pharmaciesDTOS = new ArrayList<>();
        ArrayList<Float> lista_naziva = new ArrayList<>();

        for (Pharmacy pharmacy : pharmacyList) {
            lista_naziva.add(pharmacy.getRating());

        }
        java.util.Collections.sort(lista_naziva);
        for(Float naz: lista_naziva){
            Pharmacy pharmacy=pharmacyService.findByRating(naz);
            pharmaciesDTOS.add(pharmacy);
        }


        return new ResponseEntity<>(pharmaciesDTOS, HttpStatus.OK);
    }



}
