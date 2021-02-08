package com.example.ISAISA.service;

import com.example.ISAISA.model.*;
import com.example.ISAISA.repository.AppointmentRepository;
import com.example.ISAISA.repository.CounselingRepository;
import com.example.ISAISA.repository.ExaminationRepository;
import com.example.ISAISA.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CounselingService {


    private CounselingRepository counselingRepository;
    private PatientRepository patientRepository;
    private ExaminationRepository examinationRepository;

    @Autowired
    public void setExaminationRepository(ExaminationRepository examinationRepository) {
        this.examinationRepository = examinationRepository;
    }

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

    public Boolean createCounselingPharmacist(Pharmacist pharmacist, Integer examinationId, LocalDateTime startOfCounseling, LocalDateTime endOfCounseling, Integer price){

        Examination examination = examinationRepository.findOneById(examinationId);
        Patient patient = examination.getExaminationCounseling().getPatient();
        Pharmacy pharmacy = pharmacist.getPharmacy();

        Counseling counseling = new Counseling();
        counseling.setPatient(patient);
        counseling.setPharmacist(pharmacist);
        counseling.setBeginofappointment(startOfCounseling);
        counseling.setEndofappointment(endOfCounseling);
        counseling.setPrice(price);


        Boolean ok = checkIfCounselingIsAvailable(pharmacist, patient, startOfCounseling, endOfCounseling);

        if(ok){
            counseling = counselingRepository.save(counseling);
            examination.setNewCounseling(counseling);
            examination = examinationRepository.save(examination);
            return true;
        }
        return false;

    }

    public Boolean checkIfCounselingIsAvailable(Pharmacist pharmacist, Patient patient, LocalDateTime startOfCounseling, LocalDateTime endOfCounseling){

        List<Counseling> patientCounseling = counselingRepository.findAllByPatient(patient);
        Boolean patientFree = true;

        for (Counseling i : patientCounseling) {
            if(i.getBeginofappointment().toLocalDate().isEqual(startOfCounseling.toLocalDate())) {
                if ((startOfCounseling.isAfter(i.getBeginofappointment()) && startOfCounseling.isBefore(i.getEndofappointment())) || (endOfCounseling.isAfter(i.getBeginofappointment()) && endOfCounseling.isBefore(i.getEndofappointment()))) {
                    patientFree = false;
                    break;
                }
            }
        }


        List<Counseling> pharmacistCounseling = counselingRepository.findAllByPharmacist(pharmacist);
        Boolean pharmacistFree = true;


        for (Counseling i : pharmacistCounseling) {
            if(i.getBeginofappointment().toLocalDate().isEqual(startOfCounseling.toLocalDate())) {
                if ((startOfCounseling.isAfter(i.getBeginofappointment()) && startOfCounseling.isBefore(i.getEndofappointment())) || (endOfCounseling.isAfter(i.getBeginofappointment()) && endOfCounseling.isBefore(i.getEndofappointment()))) {
                    pharmacistFree = false;
                    break;
                }
            }
        }


        LocalTime pharmacistBeginOfWork = pharmacist.getBeginofwork();
        LocalTime pharmacistEndOfWork = pharmacist.getEndofwork();

        if(pharmacistFree) {
            if (startOfCounseling.toLocalTime().isBefore(pharmacistBeginOfWork) || startOfCounseling.toLocalTime().isAfter(pharmacistEndOfWork) || endOfCounseling.toLocalTime().isBefore(pharmacistBeginOfWork) || endOfCounseling.toLocalTime().isAfter(pharmacistEndOfWork)) {
                pharmacistFree = false;
            }
        }


        if(pharmacistFree && patientFree){
            return true;
        }else {
            return false;
        }
    }

}
