package com.example.ISAISA.controller;

import com.example.ISAISA.DTO.AdminSystemRegDto;
import com.example.ISAISA.DTO.PharmacyRegDTO;
import com.example.ISAISA.model.Complaint;
import com.example.ISAISA.model.PatientDto;
import com.example.ISAISA.model.Pharmacy;
import com.example.ISAISA.model.User;
import com.example.ISAISA.repository.ConfirmationTokenRepository;
import com.example.ISAISA.repository.OfferRepository;
import com.example.ISAISA.repository.OrderRepository;
import com.example.ISAISA.repository.UserRepository;
import com.example.ISAISA.security.TokenUtils;
import com.example.ISAISA.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/systemadmins")
public class AdminSystemController {

    @Autowired
    private AdminSystemService adminSystemService;


    @Autowired
    private ComplaintService complaintService;


    @Autowired
    private PharmacyService pharmacyService;




    @Autowired
    private UserService userService;

    @Autowired
    public void setAdminSystemService(AdminSystemService adminSystemService) {
        this.adminSystemService = adminSystemService;
    }

    @PostMapping(value="/signupAdminPharmacy", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> addAdminPharmacy(@RequestBody AdminSystemRegDto adminSystemRegDto, UriComponentsBuilder ucBuilder) throws ResourceConflictException, Exception {

        User existUser = this.userService.findByEmail(adminSystemRegDto.getEmail());
        if (existUser != null) {
            throw new Exception("Postoji User");
        }

        User user = this.userService.saveAdminPharmacy(adminSystemRegDto);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user/{userId}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    @PostMapping(value="/signupAdmin",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMINSYSTEM')")
    public ResponseEntity<User> addAdmin(@RequestBody PatientDto patientDto, UriComponentsBuilder ucBuilder) throws ResourceConflictException, Exception {

        User existUser = this.userService.findByEmail(patientDto.getEmail());
        if (existUser != null) {
            throw new Exception("Postoji User");
        }

        User user = this.userService.saveAdminSystem(patientDto);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user/{userId}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    @GetMapping(value="/allcomplaints",produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<List<Complaint>> getComplaints() {
        List<Complaint> complaintList = this.complaintService.findAll();

        // Kreiramo listu DTO objekata



        return new ResponseEntity<>(complaintList, HttpStatus.OK);
    }

    @PostMapping(value="/signupPharmacy", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Pharmacy> addPharmacy(@RequestBody PharmacyRegDTO pharmacyDto, UriComponentsBuilder ucBuilder) throws ResourceConflictException, Exception {

        Pharmacy existPharmacy = this.pharmacyService.findByAddress(pharmacyDto.getAddress());
        if (existPharmacy != null) {
            throw new Exception("Postoji User");
        }

        Pharmacy pharmacy = this.pharmacyService.save(pharmacyDto);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user/{userId}").buildAndExpand(pharmacy.getId()).toUri());
        return new ResponseEntity<Pharmacy>(pharmacy, HttpStatus.CREATED);
    }




    @PostMapping(value="/signupSupplier",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<User> addSupplier(@RequestBody PatientDto patientDto, UriComponentsBuilder ucBuilder) throws ResourceConflictException, Exception {

        User existUser = this.userService.findByEmail(patientDto.getEmail());
        if (existUser != null) {
            throw new Exception("Postoji User");
        }

        User user = this.userService.saveSupplier(patientDto);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user/{userId}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    @PostMapping(value="/signupDermatologist",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMINSYSTEM')")
    public ResponseEntity<User> addAdminPharmacy(@RequestBody PatientDto patientDto, UriComponentsBuilder ucBuilder) throws ResourceConflictException, Exception {

        User existUser = this.userService.findByEmail(patientDto.getEmail());
        if (existUser != null) {
            throw new Exception("Postoji User");
        }

        User user = this.userService.saveDermatologist(patientDto);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user/{userId}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }
}
