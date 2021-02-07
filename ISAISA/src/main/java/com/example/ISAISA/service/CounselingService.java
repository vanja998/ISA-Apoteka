package com.example.ISAISA.service;

import com.example.ISAISA.model.*;
import com.example.ISAISA.repository.AppointmentRepository;
import com.example.ISAISA.repository.CounselingRepository;
import com.example.ISAISA.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class CounselingService {


    private CounselingRepository counselingRepository;
    private PatientRepository patientRepository;

    @Autowired
    public void setCounselingRepository(CounselingRepository counselingRepository) {
        this.counselingRepository = counselingRepository;
    }

    @Autowired
    public void setPatientRepository(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Counseling ifPatientHasCounseling(Integer idPatient, Pharmacist pharmacist) {

        LocalTime now = LocalTime.now();
        LocalDate today = LocalDate.now();

        List<Counseling> counselings = counselingRepository.findAll();


        for (Counseling i : counselings) {
            if (i.getPatient() != null) {
                if (i.getPatient().getId().equals(idPatient)) {
                    if (today.isEqual(i.getBeginofappointment().toLocalDate())) {
                        if (now.isAfter(i.getBeginofappointment().toLocalTime()) && now.isBefore(i.getEndofappointment().toLocalTime())) {
                            if (i.getPharmacist().getId() == pharmacist.getId()) {
                                return i;
                            }
                        }
                    }
                }
            }
        }

        return null;
    }

    public Boolean checkPharmacy(Pharmacist pharmacist) {

        LocalTime now = LocalTime.now();

        if (now.isAfter(pharmacist.getBeginofwork()) && now.isBefore(pharmacist.getEndofwork())) {
            return true;
        }
        return false;
    }

    public Integer penalPatient(Integer counselingId) {

        Counseling counseling = counselingRepository.getOne(counselingId);
        Patient patient = counseling.getPatient();
        Integer newPenalty = patient.getPenalty()+1;
        patient.setPenalty(newPenalty);
        patient = patientRepository.save(patient);
        return patient.getId();

    }

}
