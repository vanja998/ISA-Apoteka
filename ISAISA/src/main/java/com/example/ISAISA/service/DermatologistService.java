package com.example.ISAISA.service;

import com.example.ISAISA.DTO.PharmacistDTO;
import com.example.ISAISA.DTO.UserChangeDTO;
import com.example.ISAISA.model.*;
import com.example.ISAISA.repository.DermatologistRepository;
import com.example.ISAISA.repository.Dermatologist_PharmacyyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DermatologistService {

    private DermatologistRepository dermatologistRepository;
    private Dermatologist_PharmacyyRepository dermatologist_pharmacyRepository;
    @Autowired
    public void setDermatologistRepository(DermatologistRepository dermatologistRepository) {
        this.dermatologistRepository = dermatologistRepository;
    }

    @Autowired
    public void setDermatologist_pharmacyRepository(Dermatologist_PharmacyyRepository dermatologist_pharmacyRepository) {
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
            Dermatologist d = dp.getDermatologist();
            dermatologists.add(d);
        }

        return dermatologists;
    }

    public Dermatologist findById(Integer id) { return dermatologistRepository.findOneById(id); }

    public Set<Dermatologist> getDermatologistsByPharmacyAndFirstNameAndLastName(Pharmacy pharmacy, String firstName, String lastName) {

        Set<Dermatologist_Pharmacyy> dermatologist_pharmacyys = dermatologist_pharmacyRepository.findAllByPharmacy(pharmacy);

        Set<Dermatologist> dermatologists = new HashSet<>();

        for(Dermatologist_Pharmacyy dp : dermatologist_pharmacyys) {
            if (dp.getDermatologist().getFirstName().toLowerCase().equals(firstName.toLowerCase()) && dp.getDermatologist().getLastName().toLowerCase().equals(lastName.toLowerCase()))
                dermatologists.add(dp.getDermatologist());
        }

        return dermatologists;
    }

    public Dermatologist_Pharmacyy addToPharmacy(Dermatologist dermatologist, LocalTime beginOfWork, LocalTime endOfWork, Pharmacy pharmacy) {

        Dermatologist_Pharmacyy dermatologist_pharmacyy = new Dermatologist_Pharmacyy(beginOfWork, endOfWork, pharmacy, dermatologist);

        dermatologist_pharmacyy = this.dermatologist_pharmacyRepository.save(dermatologist_pharmacyy);

        return dermatologist_pharmacyy;
    }

}
