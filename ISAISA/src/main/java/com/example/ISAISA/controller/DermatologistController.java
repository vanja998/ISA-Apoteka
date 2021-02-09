package com.example.ISAISA.controller;

import com.example.ISAISA.DTO.DermatologistDTO;
import com.example.ISAISA.DTO.PharmacistDTO;
import com.example.ISAISA.DTO.UserChangeDTO;
import com.example.ISAISA.model.AdminPharmacy;
import com.example.ISAISA.model.Dermatologist;
import com.example.ISAISA.model.Pharmacy;
import com.example.ISAISA.service.DermatologistService;
import com.example.ISAISA.service.UserServiceDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping(value = "/dermatologists")
public class DermatologistController {

    private DermatologistService dermatologistService;

    @Autowired
    private UserServiceDetails userDetailsService;

    @Autowired
    public void setDermatologistService(DermatologistService dermatologistService) {
        this.dermatologistService = dermatologistService;
    }

    @GetMapping(value="/dermatologist",produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('DERMATOLOGIST')")
    public ResponseEntity<Dermatologist> getDermatologist() {
        Dermatologist user = (Dermatologist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value="/dermatologistChange", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('DERMATOLOGIST')")
    public ResponseEntity<Dermatologist> changeDermatologistInfo(@RequestBody UserChangeDTO userDTO) {

        Dermatologist user = dermatologistService.changeDermatologistInfo(userDTO);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/changepass", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('DERMATOLOGIST')")
    public ResponseEntity<?> changePassword(@RequestBody AuthenticationController.PasswordChanger passwordChanger) {

        userDetailsService.changePassword(passwordChanger.oldPassword, passwordChanger.newPassword);

        Map<String, String> result = new HashMap<String, String>();
        result.put("result", "success");

        return ResponseEntity.accepted().body(result);
    }

    @GetMapping(value="/adminDermatologists", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMINPHARMACY')")
    public ResponseEntity<Set<DermatologistDTO>> getDermatologistsByAdminPharmacy() {

        AdminPharmacy user = (AdminPharmacy) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Pharmacy pharmacy = user.getPharmacy();

        Set<Dermatologist> dermatologists = dermatologistService.getByPharmacy(pharmacy);
        Set<DermatologistDTO> dermatologistDTOS = new HashSet<>();
        for (Dermatologist d : dermatologists) {
            DermatologistDTO dermatologistDTO = new DermatologistDTO(d.getId(), d.getFirstName(), d.getLastName());
            dermatologistDTOS.add(dermatologistDTO);
        }

        return new ResponseEntity<>(dermatologistDTOS, HttpStatus.OK);
    }

    @PostMapping(value="/adminDermatologistsSearch", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMINPHARMACY')")
    public ResponseEntity<Set<DermatologistDTO>> getDermatologistByAdminPharmacyAndFirstNameAndLastName(@RequestBody DermatologistDTO dermatologistDTO) {

        AdminPharmacy user = (AdminPharmacy) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Set<Dermatologist> dermatologists = dermatologistService.getDermatologistsByPharmacyAndFirstNameAndLastName(user.getPharmacy(), dermatologistDTO.getFirstName(), dermatologistDTO.getLastName());
        Set<DermatologistDTO> dermatologistDTOS = new HashSet<>();

        for (Dermatologist dermatologist : dermatologists) {
            DermatologistDTO dermatologistDTO1 = new DermatologistDTO(dermatologist.getFirstName(), dermatologist.getLastName(), user.getPharmacy());
            dermatologistDTOS.add(dermatologistDTO1);
        }

        return new ResponseEntity<>(dermatologistDTOS, HttpStatus.OK);
    }



}
