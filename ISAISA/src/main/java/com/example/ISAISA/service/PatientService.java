package com.example.ISAISA.service;

import com.example.ISAISA.model.Patient;
import com.example.ISAISA.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientService {

    private PatientRepository patientRepository;

    @Autowired
    public void setPatientRepository(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }




    public Optional<Patient> findById(Integer patientId) {
        return patientRepository.findById(patientId);
    }
}
