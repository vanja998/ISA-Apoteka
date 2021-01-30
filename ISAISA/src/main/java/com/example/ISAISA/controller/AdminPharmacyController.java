package com.example.ISAISA.controller;

import com.example.ISAISA.DTO.PharmacistDTO;
import com.example.ISAISA.DTO.UserChangeDTO;
import com.example.ISAISA.model.AdminPharmacy;
import com.example.ISAISA.model.Pharmacist;
import com.example.ISAISA.service.AdminPharmacyService;
import com.example.ISAISA.service.PharmacistService;
import com.example.ISAISA.service.UserServiceDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping(value = "/pharmacyadmins")
public class AdminPharmacyController {

    @Autowired
    private UserServiceDetails userDetailsService;

    private AdminPharmacyService adminPharmacyService;
    private PharmacistService pharmacistService;

    @Autowired
    public void setAdminPharmacyService(AdminPharmacyService adminPharmacyService) {
        this.adminPharmacyService = adminPharmacyService;
    }

    @Autowired
    public void setPharmacistService(PharmacistService pharmacistService) {
        this.pharmacistService = pharmacistService;
    }

    @GetMapping(value="/admin",produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMINPHARMACY')")
    public ResponseEntity<AdminPharmacy> getAdmin() {
        AdminPharmacy user = (AdminPharmacy) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value="/adminChangeInfo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMINPHARMACY')")
    public ResponseEntity<AdminPharmacy> changeAdminInfo(@RequestBody UserChangeDTO userDTO) {

        AdminPharmacy user = adminPharmacyService.changeAdminInfo(userDTO);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/change-password", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMINPHARMACY')")
    public ResponseEntity<?> changePassword(@RequestBody AuthenticationController.PasswordChanger passwordChanger) {

        userDetailsService.changePassword(passwordChanger.oldPassword, passwordChanger.newPassword);

        Map<String, String> result = new HashMap<String, String>();
        result.put("result", "success");

        return ResponseEntity.accepted().body(result);
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

}
