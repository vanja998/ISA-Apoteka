package com.example.ISAISA.service;

import com.example.ISAISA.DTO.PharmacistDTO;
import com.example.ISAISA.model.Medication;
import com.example.ISAISA.model.Pharmacist;
import com.example.ISAISA.model.Pharmacy;
import com.example.ISAISA.repository.EmployeeRepository;
import com.example.ISAISA.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MedicationService {
    private MedicationRepository medicationRepository;


    @Autowired
    public void setMedicationRepository(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    public List<Medication> findAll(){
        return medicationRepository.findAll();
    }
    public Set<Medication> findAllByName(String name){return medicationRepository.findAllByName(name);}
    public Medication findByName(String name){return medicationRepository.findByName(name);}
    public Medication findById(Integer id) {return medicationRepository.findOneById(id);}

    public Set<Medication> getMedicationsbyName(String name){
        Set<Medication> medications=medicationRepository.findAllByName(name);
        Set<Medication> medicationDTOS = new HashSet<>();
        for( Medication m: medications){
            Medication meds=new Medication(m.getCode(),m.getName(),m.getType_med(),m.getShape_med(),m.getIngredients(),m.getProducer(),m.getPrescription(),m.getNotes());
            medicationDTOS.add(meds);
        }

        return medicationDTOS;
    }
}
