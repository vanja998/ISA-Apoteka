package com.example.ISAISA.controller;

import com.example.ISAISA.DTO.PatientSearchDto;
import com.example.ISAISA.DTO.PharmacistDTO;
import com.example.ISAISA.DTO.PharmacyDTO;
import com.example.ISAISA.DTO.UserChangeDTO;
import com.example.ISAISA.model.*;
import com.example.ISAISA.service.PatientService;
import com.example.ISAISA.service.UserServiceDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/patients")
public class PatientController {

    private PatientService patientService;

    @Autowired
    private UserServiceDetails userDetailsService;

    @Autowired
    public void setPatientService(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/{patientId}")
    @PreAuthorize("hasAuthority('ROLE_PATIENT')")
    public Optional<Patient> loadById(@PathVariable Integer patientId) {
        return this.patientService.findById(patientId);
    }

    @GetMapping(value="/patient",produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<Patient>getPatient () {
        Patient user = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value="/patientChangeInfo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<Patient> changeAdminInfo(@RequestBody UserChangeDTO userDTO) {

        Patient user = patientService.changePatientInfo(userDTO);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/change-password", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<?> changePassword(@RequestBody AuthenticationController.PasswordChanger passwordChanger) {

        userDetailsService.changePassword(passwordChanger.oldPassword, passwordChanger.newPassword);

        Map<String, String> result = new HashMap<String, String>();
        result.put("result", "success");

        return ResponseEntity.accepted().body(result);
    }

    @GetMapping(value="/allSearchPatientsDerma",produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('DERMATOLOGIST')")
    public ResponseEntity<List<PatientSearchDto>> getAllSearchPatientsDoctor() {
        //Dermatologist user = (Dermatologist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<PatientSearchDto> patients = patientService.getAllSearchPatients();

        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @PostMapping(value="/searchPatientsDerma", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('DERMATOLOGIST')")
    public ResponseEntity<Set<PatientSearchDto>> getSearchPatientByFirstNameAndLastName(@RequestBody PatientSearchDto patientSearchDto) {

        //AdminPharmacy user = (AdminPharmacy) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<PatientSearchDto> patients = patientService.getPatientByFirstNameAndLastName(patientSearchDto.getFirstName(), patientSearchDto.getLastName());

        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

}
