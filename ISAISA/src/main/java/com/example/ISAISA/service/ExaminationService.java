package com.example.ISAISA.service;

import com.example.ISAISA.DTO.BooleanDto;
import com.example.ISAISA.DTO.ExaminPatientDto;
import com.example.ISAISA.model.*;
import com.example.ISAISA.repository.AppointmentRepository;
import com.example.ISAISA.repository.CounselingRepository;
import com.example.ISAISA.repository.ExaminationRepository;
import com.example.ISAISA.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
public class ExaminationService {

    private ExaminationRepository examinationRepository;
    private AppointmentRepository appointmentRepository;
    private MedicationRepository medicationRepository;
    private CounselingRepository counselingRepository;

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

    @Autowired
    public void setCounselingRepository(CounselingRepository counselingRepository) {
        this.counselingRepository = counselingRepository;
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

        Patient patient; //= new Patient();
        if(examination.getExaminationAppointment() != null) {
            patient = examination.getExaminationAppointment().getPatient();
        }
        else {
             patient = examination.getExaminationCounseling().getPatient();
        }

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
        //Set<Pharmacy> pharmaciesSet = medication.getPharmacies();
        Set<Medication> medications = new HashSet<>();
        if(examination.getExaminationAppointment() != null) {
             medications = examination.getExaminationAppointment().getPharmacy_appointment().getMedication();
        }
        else {
             medications = examination.getExaminationCounseling().getPharmacist().getPharmacy().getMedication();
        }
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

        return false;

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

    public List<Appointment> getExaminatedPatients(Dermatologist dermatologist){

        List<Examination> examinations = examinationRepository.findAll();
        Integer dermatologistId = dermatologist.getId();

        List<Examination> examinationsDermatologist = new ArrayList<>();
        for(Examination i: examinations){
            if (i.getExaminationAppointment().getDermatologist().getId() == dermatologistId){
                examinationsDermatologist.add(i);
            }
        }

        List<Appointment> termini = new ArrayList<>();
        for (Examination i: examinationsDermatologist){
            termini.add(i.getExaminationAppointment());
        }

        return termini;
    }

    public List<ExaminPatientDto> getExaminatedPatientsSortName(Dermatologist dermatologist){

        List<Examination> examinations = examinationRepository.findAll();
        Integer dermatologistId = dermatologist.getId();

        List<Examination> examinationsDermatologist = new ArrayList<>();
        for(Examination i: examinations){
            if (i.getExaminationAppointment().getDermatologist().getId() == dermatologistId){
                examinationsDermatologist.add(i);
            }
        }

        List<Appointment> termini = new ArrayList<>();
        for (Examination i: examinationsDermatologist){
            termini.add(i.getExaminationAppointment());
        }

        List<ExaminPatientDto> examinPatientDtos = new ArrayList<>();
        for(Appointment i: termini){
            ExaminPatientDto examinPatientDto = new ExaminPatientDto(i.getPatient().getFirstName(), i.getPatient().getLastName(), i.getBeginofappointment().toLocalDate());
            examinPatientDtos.add(examinPatientDto);
        }

        Collections.sort(examinPatientDtos, Comparator.comparing(ExaminPatientDto::getName));

        return examinPatientDtos;
    }


    public List<ExaminPatientDto> getExaminatedPatientsSortLastName(Dermatologist dermatologist){

        List<Examination> examinations = examinationRepository.findAll();
        Integer dermatologistId = dermatologist.getId();

        List<Examination> examinationsDermatologist = new ArrayList<>();
        for(Examination i: examinations){
            if (i.getExaminationAppointment().getDermatologist().getId() == dermatologistId){
                examinationsDermatologist.add(i);
            }
        }

        List<Appointment> termini = new ArrayList<>();
        for (Examination i: examinationsDermatologist){
            termini.add(i.getExaminationAppointment());
        }

        List<ExaminPatientDto> examinPatientDtos = new ArrayList<>();
        for(Appointment i: termini){
            ExaminPatientDto examinPatientDto = new ExaminPatientDto(i.getPatient().getFirstName(), i.getPatient().getLastName(), i.getBeginofappointment().toLocalDate());
            examinPatientDtos.add(examinPatientDto);
        }

        Collections.sort(examinPatientDtos, Comparator.comparing(ExaminPatientDto::getLastName));

        return examinPatientDtos;
    }

    public List<ExaminPatientDto> getExaminatedPatientsSortDate(Dermatologist dermatologist){

        List<Examination> examinations = examinationRepository.findAll();
        Integer dermatologistId = dermatologist.getId();

        List<Examination> examinationsDermatologist = new ArrayList<>();
        for(Examination i: examinations){
            if (i.getExaminationAppointment().getDermatologist().getId() == dermatologistId){
                examinationsDermatologist.add(i);
            }
        }

        List<Appointment> termini = new ArrayList<>();
        for (Examination i: examinationsDermatologist){
            termini.add(i.getExaminationAppointment());
        }

        List<ExaminPatientDto> examinPatientDtos = new ArrayList<>();
        for(Appointment i: termini){
            ExaminPatientDto examinPatientDto = new ExaminPatientDto(i.getPatient().getFirstName(), i.getPatient().getLastName(), i.getBeginofappointment().toLocalDate());
            examinPatientDtos.add(examinPatientDto);
        }

        Collections.sort(examinPatientDtos, Comparator.comparing(ExaminPatientDto::getDate));

        return examinPatientDtos;
    }

    //*************

    public Counseling findCounseling(Pharmacist pharmacist){

        LocalTime now = LocalTime.now();
        LocalDate today = LocalDate.now();

        List<Counseling> counselings = counselingRepository.findAll();

        for (Counseling i : counselings){
            if(today.isEqual(i.getBeginofappointment().toLocalDate())){
                if(now.isAfter(i.getBeginofappointment().toLocalTime()) && now.isBefore(i.getEndofappointment().toLocalTime())){
                    if(i.getPharmacist().getId() == pharmacist.getId()) {
                        return i;
                    }
                }
            }
        }

        return null;
    }

    public Integer SaveCounseling(Counseling counseling, Pharmacist user) {
        Examination examination = new Examination();
        examination.setExaminationCounseling(counseling);
        examination = examinationRepository.save(examination);
        return examination.getId();
    }

}
