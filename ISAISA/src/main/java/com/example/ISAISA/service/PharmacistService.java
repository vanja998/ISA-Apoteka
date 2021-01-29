package com.example.ISAISA.service;

import com.example.ISAISA.DTO.PharmacistDTO;
import com.example.ISAISA.DTO.UserChangeDTO;
import com.example.ISAISA.model.AdminPharmacy;
import com.example.ISAISA.model.Pharmacist;
import com.example.ISAISA.model.Pharmacy;
import com.example.ISAISA.repository.PharmacistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class PharmacistService {

    private PharmacistRepository pharmacistRepository;

    @Autowired
    public void setPharmacistRepository(PharmacistRepository pharmacistRepository) {
        this.pharmacistRepository = pharmacistRepository;
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
}
