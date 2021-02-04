package com.example.ISAISA.service;

import com.example.ISAISA.DTO.BooleanDto;
import com.example.ISAISA.model.*;
import com.example.ISAISA.repository.AppointmentRepository;
import com.example.ISAISA.repository.ExaminationRepository;
import com.example.ISAISA.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ExaminationService {

    private ExaminationRepository examinationRepository;
    private AppointmentRepository appointmentRepository;
    private MedicationRepository medicationRepository;

    @Autowired
    public void setExaminationRepository(ExaminationRepository examinationRepository) {
        this.examinationRepository = examinationRepository;
    }

    @Autowired
    public void setAppointmentRepository(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Autowired
    public void setMedicationRepository(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    public Appointment findAppointment(Dermatologist dermatologist){

        LocalTime now = LocalTime.now();
        LocalDate today = LocalDate.now();

        List<Appointment> appointments = appointmentRepository.findAll();

        for (Appointment i : appointments){
            if(today.isEqual(i.getBeginofappointment().toLocalDate())){
                if(now.isAfter(i.getBeginofappointment().toLocalTime()) && now.isBefore(i.getEndofappointment().toLocalTime())){
                    if(i.getDermatologist().getId() == dermatologist.getId()) {
                        return i;
                    }
                }
            }
        }

        return null;
    }

    public Integer Save(Appointment appointment, Dermatologist user) {
        Examination examination = new Examination();
        examination.setExaminationAppointment(appointment);
        examination = examinationRepository.save(examination);
        return examination.getId();
    }

    /*public Integer createNewExamination(Appointment appointment, Dermatologist dermatologist){

        examinationRepository.

        return null;
    }*/

    public void writeReport(Integer examinationId, String report) {

        Examination examination = examinationRepository.findOneById(examinationId);

        examination.setReport(report);
        examination = examinationRepository.save(examination);

    }
    public List<String> getMedicationsForPrescription(Integer examinationId){

        Examination examination = examinationRepository.findOneById(examinationId);

        Patient patient = examination.getExaminationAppointment().getPatient();
        Set<Medication> allergy = patient.getMedication();

        List<Medication> medications = medicationRepository.findAll();

        List<String> imenaSvihLekova = new ArrayList<>();
        for(Medication j : medications){
            imenaSvihLekova.add(j.getName());
        }

        List<String> imenaAlergena = new ArrayList<>();
        for(Medication j : allergy){
            imenaAlergena.add(j.getName());
        }

        for(int i = 0; i < imenaSvihLekova.size(); i++){
            String ime = imenaSvihLekova.get(i);
            for(int j = 0; j< imenaAlergena.size(); j++){
                String ime2 = imenaAlergena.get(j);
                if(ime.equals(ime2)){
                    imenaSvihLekova.remove(i);
                }
            }
        }


        /*for(Medication i : allergy){
            for(Medication j : medications){
                if(i.getId() == j.getId()){
                    medications.remove(j);
                }
            }
        }*/

        return imenaSvihLekova;


    }


    public Boolean isMedicationAvailable(String medicationName, Integer examinationId){

        Medication medication = medicationRepository.findByName(medicationName);
        Examination examination = examinationRepository.findOneById(examinationId);

        //Integer pharmacyId = examination.getExaminationAppointment().getPharmacy_appointment().getId();

        //Set<Medication> medications = pharmacy.getMedication();
        //jeste ahahhahahaha hahahah ne nadje definitivno apoteke od samo sekund pliz tati trebam EVO ME na 2
        //Set<Pharmacy> pharmaciesSet = medication.getPharmacies();

        Set<Medication> medications = examination.getExaminationAppointment().getPharmacy_appointment().getMedication();

        List<Integer> lekoviId = new ArrayList<>();
        for(Medication j : medications){
            lekoviId.add(j.getId());
        }

        Integer lekId = medication.getId();

        for(int i = 0; i<lekoviId.size(); i++){
            if(lekoviId.get(i).equals(lekId)){
                return true;
            }
        }


        /*
        for(Pharmacy i: pharmaciesSet){
            Integer getId = i.getId();
            if(getId.equals(pharmacyId)){
                return true;
            }
        }*/

        /*List<Integer> apotekeId = new ArrayList<>();
        for(Pharmacy j : pharmaciesSet){
            apotekeId.add(j.getId());
        }


        for(int i = 0; i<apotekeId.size(); i++){
            Integer p = apotekeId.get(i);
            if(p.equals(pharmacyId)){
                return true;
            }
        }*/
        return false;
        /*List<Integer> lekoviId = new ArrayList<>();
        for(Medication j : medications){
            lekoviId.add(j.getId());
        }

        Integer lekId = medication.getId();

        for(int i = 0; i<lekoviId.size(); i++){
            if(lekoviId.get(i).equals(lekId)){
                return true;
            }
        }*/



        /*if(medications.contains(medication)){
            return true;
        }

        /*for(Medication i: pharmacy.getMedication()){
            if (i.getId() == medication.getId()){
                return true;
            }
        }*/


    }


    public List<String> getAlternativeMedications(Integer examinationId,String medicationName){

        Medication medication = medicationRepository.findByName(medicationName);
        List<String> notAlergic = getMedicationsForPrescription(examinationId);

        Set<Medication> alternative = medication.getMedication();

        List<String> imenaAlternativa = new ArrayList<>();
        for(Medication i : alternative){
            imenaAlternativa.add(i.getName());
        }

        List<String> alternativeNotAlergic = new ArrayList<>();

        for(int i = 0; i < imenaAlternativa.size(); i++){
            String ime = imenaAlternativa.get(i);
            for(int j = 0; j< notAlergic.size(); j++){
                String ime2 = notAlergic.get(j);
                if(ime.equals(ime2)){
                    alternativeNotAlergic.add(ime);
                }
            }
        }

        return alternativeNotAlergic;
    }


    public Boolean savePrescription(Integer examinationId, String medicationName, Integer duration) {

        Examination examination = examinationRepository.findOneById(examinationId);
        Medication medication = medicationRepository.findByName(medicationName);

        examination.setPrescriptedMedication(medication);
        examination.setTherapyDuration(duration);
        examination = examinationRepository.save(examination);

        return true;
    }
}
