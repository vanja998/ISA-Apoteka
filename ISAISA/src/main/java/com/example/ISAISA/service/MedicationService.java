package com.example.ISAISA.service;

import com.example.ISAISA.DTO.PharmacistDTO;
import com.example.ISAISA.model.Medication;
import com.example.ISAISA.model.Pharmacist;
import com.example.ISAISA.model.Pharmacy;
import com.example.ISAISA.model.PharmacyMedication;
import com.example.ISAISA.repository.EmployeeRepository;
import com.example.ISAISA.repository.MedicationRepository;
import com.example.ISAISA.repository.PharmacyMedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MedicationService {
    private MedicationRepository medicationRepository;
    private PharmacyMedicationRepository pharmacyMedicationRepository;

    @Autowired
    public void setMedicationRepository(MedicationRepository medicationRepository) { this.medicationRepository = medicationRepository; }

    @Autowired
    public void setPharmacyMedicationRepository(PharmacyMedicationRepository pharmacyMedicationRepository) { this.pharmacyMedicationRepository = pharmacyMedicationRepository; }

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

    //Prikaz
    public Set<Medication> getMedicationByPharmacy(Pharmacy pharmacy) {
        Set<PharmacyMedication> pharmacyMedications = pharmacy.getPharmacy_medications();
        Set<Medication> medications = new HashSet<>();
        for(PharmacyMedication pm : pharmacyMedications) {
            medications.add(pm.getMedication());
        }
        return medications;
    }

    public Set<Medication> getMedicationNotInPharmacy(Pharmacy pharmacy) {
        //Svi lekovi
        List<Medication> allMedicationsList = findAll();
        Set<Integer> allMedicationIds = new HashSet<>();
        for(Medication m : allMedicationsList) {
            allMedicationIds.add(m.getId());
        }

        //Lekovi koje ima
        Set<Medication> medicationsHave = getMedicationByPharmacy(pharmacy);
        Set<Integer> medicationsHaveIds = new HashSet<>();
        for(Medication m : medicationsHave) {
            medicationsHaveIds.add(m.getId());
        }

        //Lekovi koje nema
        Set<Medication> medicationsNot = new HashSet<>();
        //Set<Integer> medicationsNotIds = new HashSet<>();


        for(Integer id : allMedicationIds) {
            if (!medicationsHaveIds.contains(id)) {
                //medicationsNotIds.add(id);
                medicationsNot.add(findById(id));
            }
        }

        return medicationsNot;
    }

    //Pretraga
    public Set<Medication> getMedicationByNameAndPharmacy(Pharmacy pharmacy, String name) {
        Set<PharmacyMedication> pharmacyMedications = pharmacy.getPharmacy_medications();
        Medication medication = medicationRepository.findByNameIgnoreCase(name);
        Set<Medication> chosenMedication = new HashSet<>();

        for (PharmacyMedication pm : pharmacyMedications) {
            if (pm.getMedication().getId().equals(medication.getId())) {
                chosenMedication.add(medication);
            }
        }

        return chosenMedication;
    }

    //Dodavanje
    public PharmacyMedication addMedicationToPharmacy(PharmacyMedication pharmacyMedication) {
        return this.pharmacyMedicationRepository.save(pharmacyMedication);
    }



}
