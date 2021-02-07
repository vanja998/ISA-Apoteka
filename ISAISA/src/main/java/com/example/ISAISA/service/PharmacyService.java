package com.example.ISAISA.service;

import com.example.ISAISA.DTO.PharmacyDTO;
import com.example.ISAISA.DTO.PharmacyRegDTO;
import com.example.ISAISA.model.*;
import com.example.ISAISA.repository.PharmacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.HashSet;
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

    public Pharmacy findByRating(Float rating){return  pharmacyRepository.findByRating(rating);}

    public Pharmacy findById(Integer id) {return pharmacyRepository.findOneById(id);}

    public Set<Pharmacy> getPharmaciesbyname(String name){
        Set<Pharmacy> pharmacies=pharmacyRepository.findAllByName(name);
        Set<Pharmacy> pharmaciesDTOS = new HashSet<>();
        for( Pharmacy m: pharmacies){
            Pharmacy meds=new Pharmacy(m.getId(),m.getName(),m.getAddress(),m.getDescription(),m.getRating());
            pharmaciesDTOS.add(meds);
        }

        return pharmaciesDTOS;
    }





}
