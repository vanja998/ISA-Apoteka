package com.example.ISAISA.service;

import com.example.ISAISA.DTO.PharmacyDTO;
import com.example.ISAISA.DTO.PharmacyRegDTO;
import com.example.ISAISA.model.Authority;
import com.example.ISAISA.model.Pharmacy;
import com.example.ISAISA.model.User;
import com.example.ISAISA.model.UserRequest;
import com.example.ISAISA.repository.PharmacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Set;

@Service
public class PharmacyService {

    private PharmacyRepository pharmacyRepository;

    @Autowired
    public void setPharmacistRepository(PharmacyRepository pharmacyRepository) {
        this.pharmacyRepository = pharmacyRepository;
    }

    public Pharmacy findByAddress(String address) throws AccessDeniedException {
        Pharmacy p = pharmacyRepository.findByAddress(address);
        return p;
    }


    public Pharmacy save(PharmacyRegDTO pharmacyDTO) {
        Pharmacy p = new Pharmacy();
        p.setAddress(pharmacyDTO.getAddress());
        p.setName(pharmacyDTO.getName());
        p.setDescription(pharmacyDTO.getDescription());


        p = this.pharmacyRepository.save(p);
        return p;
    }

    public List<Pharmacy> findAll(){
        return pharmacyRepository.findAll();
    }

    public Pharmacy findByName(String name) { return pharmacyRepository.findByName(name); }

}
