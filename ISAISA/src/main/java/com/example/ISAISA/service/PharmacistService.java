package com.example.ISAISA.service;

import com.example.ISAISA.DTO.PharmacistDTO;
import com.example.ISAISA.DTO.UserChangeDTO;
import com.example.ISAISA.model.*;
import com.example.ISAISA.repository.PharmacistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PharmacistService {

    private PharmacistRepository pharmacistRepository;
    private AuthorityService authService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPharmacistRepository(PharmacistRepository pharmacistRepository) {
        this.pharmacistRepository = pharmacistRepository;
    }

    @Autowired
    public void setAuthService(AuthorityService authService) {
        this.authService = authService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Set<PharmacistDTO> getPharmacistsByPharmacy(Pharmacy pharmacy) {

        Set<Pharmacist> pharmacists = pharmacistRepository.findAllByPharmacy(pharmacy);

        Set<PharmacistDTO> pharmacistDTOS = new HashSet<>();
        for(Pharmacist p : pharmacists) {
            PharmacistDTO pharmacistDTO = new PharmacistDTO(p.getFirstName(), p.getLastName(), p.getPharmacy(), p.getRating());
            pharmacistDTOS.add(pharmacistDTO);
        }

        return pharmacistDTOS;
    }

    public Set<PharmacistDTO> getPharmacistsByPharmacyAndFirstNameAndLastName(Pharmacy pharmacy, String firstName, String lastName) {

        Set<Pharmacist> pharmacists = pharmacistRepository.findAllByPharmacyAndFirstNameIgnoreCaseAndLastNameIgnoreCase(pharmacy, firstName, lastName);

        Set<com.example.ISAISA.DTO.PharmacistDTO> pharmacistDTOS = new HashSet<>();
        for(Pharmacist p : pharmacists) {
            com.example.ISAISA.DTO.PharmacistDTO pharmacistDTO = new com.example.ISAISA.DTO.PharmacistDTO(p.getFirstName(), p.getLastName(), p.getPharmacy(), p.getRating());
            pharmacistDTOS.add(pharmacistDTO);
        }

        return pharmacistDTOS;
    }

    public Pharmacist save(PharmacistDTO pharmacistDTO) {
        Pharmacist pharmacist = new Pharmacist();
        pharmacist.setFirstName(pharmacistDTO.getFirstName());
        pharmacist.setLastName(pharmacistDTO.getLastName());
        pharmacist.setEmail(pharmacistDTO.getEmail());
        pharmacist.setPassword(passwordEncoder.encode(pharmacistDTO.getPassword()));
        pharmacist.setAddress(pharmacistDTO.getAddress());
        pharmacist.setPhone(pharmacistDTO.getPhone());
        pharmacist.setCity(pharmacistDTO.getCity());
        pharmacist.setCountry(pharmacistDTO.getCountry());
        pharmacist.setPharmacy(pharmacistDTO.getPharmacy());

        pharmacist.setEnabled(true);

        List<Authority> auth = authService.findByname("ROLE_PHARMACIST");
        pharmacist.setAuthorities(auth);

        pharmacist = this.pharmacistRepository.save(pharmacist);
        return pharmacist;
    }


}
