package com.example.ISAISA.controller;

import com.example.ISAISA.DTO.FilterEmployeesDTO;
import com.example.ISAISA.DTO.PharmacistDTO;
import com.example.ISAISA.model.AdminPharmacy;
import com.example.ISAISA.model.Pharmacist;
import com.example.ISAISA.model.Pharmacy;
import com.example.ISAISA.service.PharmacistService;
import com.example.ISAISA.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "/pharmacists")
public class PharmacistController {

    private PharmacistService pharmacistService;
    private PharmacyService pharmacyService;

    @Autowired
    public void setPharmacistService(PharmacistService pharmacistService) {
        this.pharmacistService = pharmacistService;
    }

    @Autowired
    public void setPharmacyService(PharmacyService pharmacyService) {
        this.pharmacyService = pharmacyService;
    }

    @GetMapping(value="/adminPharmacists", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMINPHARMACY')")
    public ResponseEntity<Set<PharmacistDTO>> getPharmacistsByAdminPharmacy() {

        AdminPharmacy user = (AdminPharmacy) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<PharmacistDTO> pharmacists = pharmacistService.getPharmacistsByPharmacy(user.getPharmacy());

        return new ResponseEntity<>(pharmacists, HttpStatus.OK);
    }

    @PostMapping(value="/adminPharmacistsSearch", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMINPHARMACY')")
    public ResponseEntity<Set<PharmacistDTO>> getPharmacistsByAdminPharmacyAndFirstNameAndLastName(@RequestBody PharmacistDTO pharmacistDTO) {

        AdminPharmacy user = (AdminPharmacy) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<PharmacistDTO> pharmacists = pharmacistService.getPharmacistsByPharmacyAndFirstNameAndLastName(user.getPharmacy(),
                pharmacistDTO.getFirstName(), pharmacistDTO.getLastName());

        return new ResponseEntity<>(pharmacists, HttpStatus.OK);
    }

    @PostMapping(value="/adminPharmacistsAdd", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMINPHARMACY')")
    public ResponseEntity<Pharmacist> savePharmacist(@RequestBody PharmacistDTO pharmacistDTO) {

        AdminPharmacy user = (AdminPharmacy) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        pharmacistDTO.setPharmacy(user.getPharmacy());
        Pharmacist pharmacist = pharmacistService.save(pharmacistDTO);

        return new ResponseEntity<>(pharmacist, HttpStatus.OK);
    }

    @PostMapping(value="/adminPharmacistsFilter", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMINPHARMACY')")
    public ResponseEntity<Set<PharmacistDTO>> getPharmacistsByRatingBetween(@RequestBody FilterEmployeesDTO pharmacistDTO) {

        Float ratingUnder = (float) pharmacistDTO.getRatingUnder();
        Float ratingOver = (float) pharmacistDTO.getRatingOver();

        Set<PharmacistDTO> pharmacists = pharmacistService.getPharmacistsByRatingBetween(ratingOver, ratingUnder);

        return new ResponseEntity<>(pharmacists, HttpStatus.OK);
    }

    @GetMapping(value="/allPharmacists", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMINPHARMACY')")
    public ResponseEntity<Set<PharmacistDTO>> getAllPharmacists() {

        Set<PharmacistDTO> pharmacists = pharmacistService.getAll();

        return new ResponseEntity<>(pharmacists, HttpStatus.OK);
    }

    @PostMapping(value="/allPharmacistsSearch", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMINPHARMACY')")
    public ResponseEntity<Set<PharmacistDTO>> getPharmacistsByFirstNameAndLastName(@RequestBody PharmacistDTO pharmacistDTO) {

        AdminPharmacy user = (AdminPharmacy) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<PharmacistDTO> pharmacists = pharmacistService.getPharmacistsByFirstNameAndLastName(
                pharmacistDTO.getFirstName(), pharmacistDTO.getLastName());

        return new ResponseEntity<>(pharmacists, HttpStatus.OK);
    }

    @PostMapping(value="/allPharmacistsFilter", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMINPHARMACY')")
    public ResponseEntity<Set<PharmacistDTO>> getPharmacistsByRatingBetweenAndPharmacy(@RequestBody FilterEmployeesDTO pharmacistDTO) {

        Pharmacy pharmacy = pharmacyService.findByName(pharmacistDTO.getPharmacyName());
        Float ratingUnder = (float) pharmacistDTO.getRatingUnder();
        Float ratingOver = (float) pharmacistDTO.getRatingOver();

        Set<PharmacistDTO> pharmacists = pharmacistService.getPharmacistsByRatingBetweenAndPharmacyName(
                ratingOver, ratingUnder, pharmacy);

        return new ResponseEntity<>(pharmacists, HttpStatus.OK);
    }

}
