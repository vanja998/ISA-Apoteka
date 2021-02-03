package com.example.ISAISA.service;

import com.example.ISAISA.DTO.UserChangeDTO;
import com.example.ISAISA.model.Dermatologist;
import com.example.ISAISA.model.Dermatologist_Pharmacyy;
import com.example.ISAISA.model.Pharmacy;
import com.example.ISAISA.repository.DermatologistRepository;
import com.example.ISAISA.repository.Dermatologist_PharmacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class DermatologistService {

    private DermatologistRepository dermatologistRepository;
    private Dermatologist_PharmacyRepository dermatologist_pharmacyRepository;
    @Autowired
    public void setDermatologistRepository(DermatologistRepository dermatologistRepository) {
        this.dermatologistRepository = dermatologistRepository;
    }

    @Autowired
    public void setDermatologist_pharmacyRepository(Dermatologist_PharmacyRepository dermatologist_pharmacyRepository) {
        this.dermatologist_pharmacyRepository = dermatologist_pharmacyRepository;
    }

    public Dermatologist changeDermatologistInfo(UserChangeDTO userDTO) {

        Dermatologist user = (Dermatologist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setAddress(userDTO.getAddress());
        user.setPhone(userDTO.getPhone());
        user.setCity(userDTO.getCity());
        user.setCountry(userDTO.getCountry());

        dermatologistRepository.save(user);

        return user;
    }

    public Set<Dermatologist> getByPharmacy(Pharmacy pharmacy) {

        Set<Dermatologist_Pharmacyy> dermatologist_pharmacies = dermatologist_pharmacyRepository.findAllByPharmacy(pharmacy);
        Set<Dermatologist> dermatologists = new HashSet<>();

        for (Dermatologist_Pharmacyy dp : dermatologist_pharmacies) {
            Dermatologist d = dp.getDermatologist_id();
            dermatologists.add(d);
        }

        return dermatologists;
    }

    public Dermatologist findById(Integer id) { return dermatologistRepository.findOneById(id); }

}
